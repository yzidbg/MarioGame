/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScrollGame;

import Controlador.JugadorController;
import Modelo.Jugador;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import javax.swing.*;
import org.apache.commons.codec.binary.Hex;

/**                           
 *
 * @author yz
 */
public class ClienteLAN_TCP extends JFrame implements ActionListener, Runnable{
    private final int alto=310;
    private final int ancho=400;
    private String titVent;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JEditorPane panel;
    private JButton btnConect;
    private JTextField txtNomPlayer;
    private JPasswordField txtPassword;
    private int count;
    //Declaramos las variables necesarias para la conexion y comunicacion
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 2027;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private String host = "localhost";
    //private String host = "192.168.10.143";
    private String mensajes = "";
    private String nomPlayer;
    private boolean remoteStart;
    
    Jugador jugador = null;
    JugadorController controladorJugador = new JugadorController();
    private MessageDigest  md = null;
    
    
    

    public ClienteLAN_TCP(String titulo) throws HeadlessException {
        setTitle(titulo);
        initComp();
        view();
    }
    
    public void initComp() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        titVent= "Esperando jugadores...";
        l1= new JLabel(titVent);
        count=30;
        l2= new JLabel(String.valueOf(count));
        l3= new JLabel("Segs");
        l4= new JLabel("Nombre:");
        l5= new JLabel("Contraseña:");
        panel= new JEditorPane();
        btnConect = new JButton("Conectar");
        txtNomPlayer= new JTextField();
        txtPassword = new JPasswordField();
        setLayout(null);
        add(l1);
        l1.setBounds((ancho-200)/2, 10, 200,20);
        add(l2);
        l2.setBounds(310, 10, 30, 20);
        add(l3);
        l3.setBounds(330, 10, 40, 20);
        add(l4);
        l4.setBounds(20, 220, 100, 20);
        add(l5);
        l5.setBounds(20, 250, 100, 20);
        add(panel);
        panel.setBounds(20, 40,ancho-40, 170);
        panel.setEditable(false);
        panel.setContentType("text/html");
        add(btnConect);
        btnConect.setBounds(280, 250, 100, 20);
        btnConect.addActionListener(this);
        add(txtNomPlayer);
        txtNomPlayer.setBounds(110, 220, 150, 20);
        add(txtPassword);
        txtPassword.setBounds(110,250,150,20);
        txtNomPlayer.requestFocus(true);
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
        if(buscarJugador(txtNomPlayer.getText().trim())){
            if(Integer.parseInt(jugador.getIdTipoJug())==200){
                if(login()){
                    try{
                         // organizacion de direccion y Nro de puerto
                        cliente = new Socket(host,puerto);
                        in = new DataInputStream(cliente.getInputStream());
                        out = new DataOutputStream(cliente.getOutputStream());
                        btnConect.setEnabled(false);
                        txtNomPlayer.setEditable(false);
                        nomPlayer=txtNomPlayer.getText();
                        Thread hilo = new Thread(this);
                        hilo.start();
                        enviarMsg(nomPlayer);
                        enviarMsg("Cliente "+nomPlayer+" conectado");
                        mensajes="";
                   }catch(IOException ex){
                       ex.printStackTrace();
                   }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Error de contraseña; intente de nuevo");
                    txtNomPlayer.setText(null);
                    txtNomPlayer.requestFocus(true);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Acceso Restringido. Sólo para jugadores-user!");
                txtNomPlayer.setText(null);
                txtPassword.setText(null);
                txtNomPlayer.requestFocus(true);
            }
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
            txtNomPlayer.setText(null);
            txtPassword.setText(null);
            txtNomPlayer.requestFocus(true);
        }
    }
    
    private boolean buscarJugador(String nick){
        jugador = controladorJugador.consultarUnJugador("nick", nick);
        if (jugador==null) return false;
        else return true;
    }
    
    private boolean login(){
        String pwrSal = new String();
        byte[] mb=null;
        char [] p = txtPassword.getPassword();
        String pwr = charToStringM(p);
        try{
            md= MessageDigest.getInstance("SHA-1");
            md.update(pwr.getBytes());
            mb = md.digest();
            pwrSal=charToStringM(Hex.encodeHex(mb));
        }catch(Exception e){}
        
        if(jugador.getPassword().equals(pwrSal))return true;
        else return false;
    }
    
    private String charToStringM(char [] c){
        String r="";
        for(int i =0; i<c.length; i++){
            r+=c[i];
        }
        return r;
    }

    @Override
    public void run() {
        try{
            String s;
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el panel
            while(true){
                s=null;
                s=in.readUTF();
                if(s.equals("start")){
                    this.remoteStart=true;
                }else{
                    mensajes += "<br>"+s;
                    panel.setText(mensajes);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Funcion sirve para enviar mensajes al servidor
    public void enviarMsg(String msg){
        try {
            out.writeUTF(msg);
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
    
    
}
