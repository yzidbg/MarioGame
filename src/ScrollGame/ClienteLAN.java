/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScrollGame;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;
import javax.swing.*;

/**                                p.startGame();
                                f.dispose();

 *
 * @author yz
 */
public class ClienteLAN extends JFrame{
    private final int alto=300;
    private final int ancho=400;
    private String titVent;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private TextArea listJugad;
    private int count;
    

    public ClienteLAN(String titulo) throws HeadlessException {
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
        listJugad= new TextArea();
        setLayout(null);
        add(l1);
        l1.setBounds((ancho-200)/2, 10, 200,20);
        add(l2);
        l2.setBounds(310, 10, 30, 20);
        add(l3);
        l3.setBounds(330, 10, 40, 20);
        add(listJugad);
        
        listJugad.setBounds(10, 40,ancho-20, alto-50);
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
    
}
