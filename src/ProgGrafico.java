

import static java.lang.Thread.sleep;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

public class ProgGrafico extends javax.swing.JFrame {

    JFreeChart grafico;
    ChartPanel PainelGrafico;
    TimeSeries seriesValor;
    TimeSeriesCollection SerieDados;
    double value = 0;
    Second current = new Second();
    PacMonitor monitor;

    public ProgGrafico() throws RemoteException, NotBoundException, InterruptedException {
        initComponents();
        iniciarGrafico();

    }

    private void iniciarGrafico() throws RemoteException, NotBoundException, InterruptedException {
        /*Inicialização de variáveis*/
        seriesValor = new TimeSeries("Tamanho");
        SerieDados = new TimeSeriesCollection();
        SerieDados.addSeries(seriesValor);

        /*Rotina de Criação inicial do Gráfico*/
        XYDataset dados = SerieDados;
        grafico = ChartFactory.createTimeSeriesChart("Tamanho dos Pacotes do Servidor",
                "Tempo", "Tamanho do Pacote", dados);
        PainelGrafico = new ChartPanel(grafico, true);
        PainelGrafico.setSize(pnlGrafico.getWidth(), pnlGrafico.getHeight());
        PainelGrafico.setVisible(true);

        /*Inicializar o gráfico no Panel*/
        pnlGrafico.removeAll(); //limpar o painel
        PainelGrafico.setVisible(true);//tornar o gráfico visivel
        pnlGrafico.add(PainelGrafico); //finalmente, adicionar o gráfico ao painel
        pnlGrafico.repaint();//repintar com os gráficos iniciais

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGrafico = new javax.swing.JPanel();
        btnGrafico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFPorta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exemplo Gráfico usando JFreeChart");

        pnlGrafico.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlGraficoLayout = new javax.swing.GroupLayout(pnlGrafico);
        pnlGrafico.setLayout(pnlGraficoLayout);
        pnlGraficoLayout.setHorizontalGroup(
            pnlGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        pnlGraficoLayout.setVerticalGroup(
            pnlGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        btnGrafico.setText("Gerar Gráfico");
        btnGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoActionPerformed(evt);
            }
        });

        jLabel1.setText("IP do Servidor:");

        jLabel2.setText("Porta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(pnlGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGrafico)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoActionPerformed

        Registry regcli;
        try {
            regcli = LocateRegistry.getRegistry(jTFIP.getText(), Integer.valueOf(jTFPorta.getText()));
            monitor = (PacMonitor) regcli.lookup("PacServer");
        } catch (RemoteException ex) {
            Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        current = new Second();//pegar o tempo atual em mili
//                        seriesValor.addOrUpdate(current, 10 + Math.random());//adicionar um número randômico de teste
                        seriesValor.addOrUpdate(current, monitor.chama().get(0));
                        sleep(1000);//esperar 1 segundo
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }//GEN-LAST:event_btnGraficoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProgGrafico().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgGrafico.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrafico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFPorta;
    private javax.swing.JPanel pnlGrafico;
    // End of variables declaration//GEN-END:variables
}
