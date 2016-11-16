/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConexionController;
import Controlador.JugadorController;
import Modelo.Conexion;
import Modelo.Jugador;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;

/**
 *
 * @author yz
 */
public class ReportesUsuario extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    
    JugadorController controladorJugador = new JugadorController();
    ConexionController controladorConexion = new ConexionController();
    private String nick;
    private int score;
    private DefaultTableModel modeloRes = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };
    Jugador jugador = null;
    
    public ReportesUsuario(String nick, int score) {
        this.nick=nick;
        this.score=score;
        initComponents();
        setearValores();
        //obtenerJugadas();
        enviarDatos();
        setearTablaRes();
        fillTablaTop10();
    }
    
    private void setearTablaRes(){
        modeloRes.addColumn("#");
        modeloRes.addColumn("NickName");
        modeloRes.addColumn("DirIP");
        modeloRes.addColumn("FechaHora");
        modeloRes.addColumn("Pts");
        tablaRes.setModel(modeloRes);
    }
    
    private void fillTablaTop10(){
        //setearTablaTop10();
        Conexion c = new Conexion();
        ArrayList a= controladorConexion.consultarTop10();
        for(int i=0; i<a.size();i++){
            String s;
            c = (Conexion) a.get(i);        // TODO add your handling code here:
            Object [] fila ={i+1,c.getNick(),c.getIpCon(),c.getFechaHora(),c.getPuntos()};
            modeloRes.addRow(fila);
        }
        a=null;
        tablaRes.getColumnModel().getColumn(0).setPreferredWidth(8);
        tablaRes.getColumnModel().getColumn(1).setPreferredWidth(40);
        tablaRes.getColumnModel().getColumn(2).setPreferredWidth(60);
        tablaRes.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaRes.getColumnModel().getColumn(4).setPreferredWidth(20);
    }
    
    private void setearValores(){
        lblPts.setText("Jugador: "+nick+"; Puntos: "+score);
    }
    
    private void obtenerJugadas(){
        Conexion c = new Conexion();
        ArrayList a = controladorConexion.consultarNumConex(jugador.getId());
        c = (Conexion) a.get(0);
        //lblJugadas.setText(c.getCnx());
    }
    
    private void enviarDatos(){
        if(buscarJugador(nick)==true){
            Conexion c = new Conexion();
            try{
                //c.setIpCon(InetAddress.getLocalHost().getHostAddress());
                c.setIpCon(getIP());
            }catch(Exception e){}
            c.setFechaHora(fechaHoraAct());
            c.setPuntos(String.valueOf(score));
            c.setIdJugador(jugador.getId());
            controladorConexion.addConexion(c);
        }
    }
    
    public String getIP() {
        String publicIP;
    	try {
                URL tempURL = new URL("http://checkip.amazonaws.com");
                HttpURLConnection tempConn = (HttpURLConnection)tempURL.openConnection();
                InputStream tempInStream = tempConn.getInputStream();
                InputStreamReader tempIsr = new InputStreamReader(tempInStream);
                BufferedReader tempBr = new BufferedReader(tempIsr);        
 
                publicIP = tempBr.readLine();
 
                tempBr.close();
                tempInStream.close();
 
        } catch (Exception ex) {
                publicIP = "<No es posible resolver la direccion IP>";   
          }
 
         return publicIP;
    }
    
    private String fechaHoraAct(){
        Calendar cal1 = Calendar.getInstance();
        return cal1.get(Calendar.YEAR)+"-"+cal1.get(Calendar.MONTH)+
                "-"+cal1.get(Calendar.DATE)+" "+cal1.get(Calendar.HOUR_OF_DAY)+
                ":"+cal1.get(Calendar.MINUTE)+":"+cal1.get(Calendar.SECOND)+
                "."+cal1.get(Calendar.MILLISECOND);
    }
    
    private void evalMaxPts(){
        if(Integer.parseInt(jugador.getMaxPts())<score){
            controladorJugador.modificarMaxPts(jugador.getId(), String.valueOf(score));
        }
    }
    
    private boolean buscarJugador(String nick){
        jugador = controladorJugador.consultarUnJugador("nick", nick);
        System.err.println(jugador.toString());
        if (jugador==null) return false;
        else return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblPts = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 380));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Reporte Jugador");

        tablaRes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaRes);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Juego actual:");

        lblPts.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        lblPts.setText("pts");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("Top 10 Jugadores Puntuaciones Altas:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPts)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblPts))
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPts;
    private javax.swing.JTable tablaRes;
    // End of variables declaration//GEN-END:variables
}
