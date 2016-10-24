package ScrollGame;

//**********************************
import java.io.*;   
import java.net.*;   
import java.awt.*;   
import java.awt.event.*;   
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;   
public class ClienteLANv2_UDP extends JFrame implements ActionListener, Runnable{  
    private final int alto=300;
    private final int ancho=400;
    private String titVent;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JEditorPane panel;
    private JButton btnConect;
    private JTextField txtNomPlayer;
    private int count;
    //Declaramos las variables necesarias para la conexion y comunicacion
    private DatagramSocket socket; 
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 5000;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private InetAddress hostServer;
    //private String host = "192.168.10.143";
    private String mensajes = "";
    private String nomPlayer;
    private boolean remoteStart;
    
    
    

    public ClienteLANv2_UDP(String titulo) throws HeadlessException {
        setTitle(titulo);
        initComp();
        view();
        try {
            socket = new DatagramSocket();   
        }   
        // atrapar los problemas que pueden ocurrir al crear objeto DatagramSocket   
        catch( SocketException excepcionSocket ) {   
            excepcionSocket.printStackTrace();
        }
    }
    
    public void initComp() {
        //Direccion ip del server
        try {
            hostServer = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteLANv2_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        titVent= "Esperando jugadores...";
        l1= new JLabel(titVent);
        count=30;
        l2= new JLabel(String.valueOf(count));
        l3= new JLabel("Segs");
        l4= new JLabel("Nombre:");
        panel= new JEditorPane();
        btnConect = new JButton("Conectar");
        txtNomPlayer= new JTextField();
        txtNomPlayer.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                if (txtNomPlayer.getText().length()== 7)e.consume();
                }
            public void keyPressed(KeyEvent arg0) {}
            public void keyReleased(KeyEvent arg0) {}});
        
        setLayout(null);
        add(l1);
        l1.setBounds((ancho-200)/2, 10, 200,20);
        add(l2);
        l2.setBounds(310, 10, 30, 20);
        add(l3);
        l3.setBounds(330, 10, 40, 20);
        add(l4);
        l4.setBounds(20, 220, 80, 20);
        add(panel);
        panel.setBounds(20, 40,ancho-40, 170);
        panel.setEditable(false);
        panel.setContentType("text/html");
        add(btnConect);
        btnConect.setBounds(280, 220, 100, 20);
        btnConect.addActionListener(this);
        add(txtNomPlayer);
        txtNomPlayer.setBounds(90, 220, 150, 20);
        
    }   //fin del método inicializarComponentes
    
    public void view(){
        setSize(ancho, alto);
        setVisible(true);
        contador();
    }
    
    private void contador(){
        new Thread(new Runnable() {
                public void run() {
                    while(count>=0)
                        try {
                            l2.setText(String.valueOf(count));
                            if(count==0){
                                ajusteSalida();
                            }
                            count--;
                            Thread.sleep(1000);
			} catch (Exception e) {}
                }
            }).start();
	
    }
    
    private void ajusteSalida(){
        l1.setText("Resultados del Juego");
        l2.setText("");
        l3.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btnConect.setEnabled(false);
        txtNomPlayer.setEditable(false);
        nomPlayer=txtNomPlayer.getText();
        Thread hilo = new Thread(this);
        hilo.start();
        enviarMsg(nomPlayer);
        enviarMsg("Cliente "+nomPlayer+" conectado");
        mensajes="";
    }

    @Override
    public void run() {
        try{
            String s;
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el panel
            while(true){
                byte datos[] = new byte[100];   
                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);   
                socket.receive(recibirPaquete); // esperar el paquete 
                s= new String( recibirPaquete.getData(), 0, recibirPaquete.getLength());
                System.err.println("el mensaje recibido en el cliente: "+s);
                if(s.equals("start")){
                    this.remoteStart=true;
                }else{
                    mensajes += "<br>"+s;
                    panel.setText(mensajes);
                }
            }
        }catch(Exception e){
            System.err.println(e.toString() + "\n" ); 
            e.printStackTrace();
        }
    }
    
    //Funcion sirve para enviar mensajes al servidor
    public void enviarMsg(String msg){
        try {
            byte datos[] = msg.getBytes();   
            // crear enviarPaquete   
            DatagramPacket packSaliente = new DatagramPacket( datos,   
                datos.length, InetAddress.getLocalHost(), 5000);   
            socket.send(packSaliente); // enviar paquete   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNomPlayer() {
        return nomPlayer;
    }

    public boolean isRemoteStart() {
        return remoteStart;
    }
    
  
}  // fin de la clase Cliente  
