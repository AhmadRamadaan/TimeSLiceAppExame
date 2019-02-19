package com.app.timeSlice;

import java.awt.Color;
import java.util.Random;

public class ContentPanel extends javax.swing.JPanel implements UICallback {

    public ContentPanel() {
        initComponents();
        runnable = new CustomRunnable(new Random().nextInt(100), this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_pause = new javax.swing.JButton();
        bt_Start = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        bt_pause.setText("Pause");
        bt_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pauseActionPerformed(evt);
            }
        });

        bt_Start.setText("Start");
        bt_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_StartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_Start)
                        .addGap(18, 18, 18)
                        .addComponent(bt_pause)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_pause)
                        .addComponent(bt_Start)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_StartActionPerformed
        if (thread == null) {
            thread = new Thread(runnable);
            thread.start();
            bt_Start.setText("Resume");
            setBackground(Color.GREEN);
        }
        runnable.startRunning();
    }//GEN-LAST:event_bt_StartActionPerformed

    private void bt_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pauseActionPerformed
        runnable.stopRunning();
        setBackground(Color.YELLOW);
    }//GEN-LAST:event_bt_pauseActionPerformed

    @Override
    public void threadStart() {
        jProgressBar1.setMaximum(runnable.getTotal());
    }

    @Override
    public void threadWork() {
        jProgressBar1.setValue(runnable.getCurrent());
        jLabel1.setText(runnable.getCurrent() + "from" + runnable.getTotal());
    }

    @Override
    public void threadEnd() {
//        JOptionPane.showMessageDialog(this, "end...");
//        setBackground(Color.YELLOW);
    }

    public int getTotal() {
        return runnable.getTotal();
    }

    public CustomRunnable getRunnable() {
        return runnable;
    }

    public Thread getThread() {
        return thread;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Start;
    private javax.swing.JButton bt_pause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    private final CustomRunnable runnable;
    private Thread thread;

}
