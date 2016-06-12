

import static java.lang.Thread.sleep;
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

    public ProgGrafico() throws RemoteException, NotBoundException, InterruptedException {
        initComponents();
        iniciarGrafico();

    }

    private void iniciarGrafico() throws RemoteException, NotBoundException, InterruptedException {
        /*Inicialização de variáveis*/
        seriesValor = new TimeSeries("Valor");
        SerieDados = new TimeSeriesCollection();
        SerieDados.addSeries(seriesValor);

        /*Rotina de Criação inicial do Gráfico*/
        XYDataset dados = SerieDados;
        grafico = ChartFactory.createTimeSeriesChart("Exemplo Gráfico Times Series",
                "Tempo", "Valores", dados);
        PainelGrafico = new ChartPanel(grafico, true);
        PainelGrafico.setSize(pnlGrafico.getWidth(), pnlGrafico.getHeight());
        PainelGrafico.setVisible(true);

        /*Inicializar o gráfico no Panel*/
        pnlGrafico.removeAll(); //limpar o painel
        PainelGrafico.setVisible(true);//tornar o gráfico visivel
        pnlGrafico.add(PainelGrafico); //finalmente, adicionar o gráfico ao painel
        pnlGrafico.repaint();//repintar com os gráficos iniciais
        
        Registry regcli = LocateRegistry.getRegistry("127.0.0.1", 1169);
        PacMonitor monitor = (PacMonitor) regcli.lookup("PacServer");
        for (int i = 0; i < 10; ++i) {
            System.out.println("tamanho: " + monitor.chama().get(i));
            sleep(1000);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGrafico = new javax.swing.JPanel();
        btnGrafico = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGrafico)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoActionPerformed

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    current = new Second();//pegar o tempo atual em mili
                    seriesValor.addOrUpdate(current, 10 + Math.random());//adicionar um número randômico de teste
                    try {
                        sleep(1000);//esperar 1 segundo
                    } catch (InterruptedException ex) {
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
    private javax.swing.JPanel pnlGrafico;
    // End of variables declaration//GEN-END:variables
}
