package com.app.timeSlice;

import java.awt.Component;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        pn_ParentContent.setLayout(new BoxLayout(pn_ParentContent, BoxLayout.Y_AXIS));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_TimeSlice = new javax.swing.JButton();
        bt_Add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn_ParentContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_TimeSlice.setText("Start Time Slice 10 unit");
        bt_TimeSlice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TimeSliceActionPerformed(evt);
            }
        });

        bt_Add.setText("Add Process");
        bt_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_ParentContentLayout = new javax.swing.GroupLayout(pn_ParentContent);
        pn_ParentContent.setLayout(pn_ParentContentLayout);
        pn_ParentContentLayout.setHorizontalGroup(
            pn_ParentContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );
        pn_ParentContentLayout.setVerticalGroup(
            pn_ParentContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pn_ParentContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_TimeSlice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_TimeSlice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_TimeSliceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TimeSliceActionPerformed

        new Thread(() -> {

            LinkedList<ContentPanel> list = new LinkedList();
            Component[] components = pn_ParentContent.getComponents();
            ContentPanel pn_childContent;
            JButton bt_start;
            int componentCount = pn_ParentContent.getComponentCount();
            int count10Unit = 0;
            CustomRunnable runnable;

            for (Component component : components) {
                pn_childContent = (ContentPanel) component;
                list.add(pn_childContent);
            }

            boolean finish = false;
            while (!finish) {
                for (ContentPanel list_pnChild : list) {

                    runnable = list_pnChild.getRunnable();
                    bt_start = (JButton) list_pnChild.getComponent(2);

                    if (runnable.getCurrent() - 1 != runnable.getTotal()) {
                        bt_start.doClick();

                        int BarLess10 = 0;// for Progress less than 10 Unit 
                        count10Unit = runnable.getCurrent() + 10;

                        while (runnable.getCurrent() - 1 != runnable.getTotal()) {
                            BarLess10++;
                            if (runnable.getCurrent() == count10Unit) {
                                runnable.stopRunning();
                                break;
                            }
                            if (BarLess10 == 10 && runnable.getTotal() <= 10) {
                                break;
                            }
                        }
                    }
                }
                /**
                 * condition for break Main Loop 
                 * its not important for code but
                 * its important for High performance because it block Main loop
                 */
                int i = 0;
                for (i = 0; i < componentCount; i++) {
                    if (list.get(i).getRunnable().getCurrent() - 1 == list.get(i).getRunnable().getTotal()) {
                    } else {
                        break;//for loop 
                    }
                }
                if (i == componentCount) {
                    finish = true;
                }
                //end condtion for break Main Loop  
            }
        }).start();


    }//GEN-LAST:event_bt_TimeSliceActionPerformed

    private void bt_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddActionPerformed

        pn_ParentContent.add(new ContentPanel());
        pn_ParentContent.revalidate();


    }//GEN-LAST:event_bt_AddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Add;
    private javax.swing.JButton bt_TimeSlice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_ParentContent;
    // End of variables declaration//GEN-END:variables

}
