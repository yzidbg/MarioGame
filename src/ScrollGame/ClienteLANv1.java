/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScrollGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import javax.swing.ScrollPaneConstants;
/**                           
 *
 * @author yz
 */
public class ClienteLANv1 extends JFrame implements ActionListener, Runnable{
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
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 2027;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private String host = "localhost";
    private String mensajes = "";
    private String nomPlayer;
    
    
    

    public ClienteLANv1(String titulo) throws HeadlessException {
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
        panel= new JEditorPane();
        btnConect = new JButton("Conectar");
        txtNomPlayer= new JTextField();
        setLayout(null);
        add(l1);
        l1.setBounds((ancho-200)/2, 10, 200,20);
        add(l2);
        l2.setBounds(310, 10, 30, 20);
        add(l3);
        l3.setBounds(330, 10, 40, 20);
        add(l4);
        l4.setBounds(20, 250, 80, 20);
        add(panel);
        panel.setBounds(20, 40,ancho-40, 200);
        panel.setEditable(false);
        panel.setContentType("text/html");
        add(btnConect);
        btnConect.setBounds(280, 250, 100, 20);
        btnConect.addActionListener(this);
        add(txtNomPlayer);
        txtNomPlayer.setBounds(90, 250, 150, 20);
        
    }   //fin del método inicializarComponentes
    
    public void view(){
        setSize(ancho, alto);
        setVisible(true);
        contador();
    }
    
    private void contador(){
        new Thread(new Runnable() {
                public void run() {
                    while(count>0)
                        try {
                            l2.setText(String.valueOf(count));
                            count--;
                            Thread.sleep(1000);
			} catch (Exception e) {}
                }
            }).start();
	
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    @Override
    public void run() {
        try{
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el panel
            while(true){
                mensajes += in.readUTF();
                panel.setText(mensajes);
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
    
}
