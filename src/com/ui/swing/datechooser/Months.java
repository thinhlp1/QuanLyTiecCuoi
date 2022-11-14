package com.ui.swing.datechooser;

import com.ui.swing.datechooser.Event;
import com.ui.swing.datechooser.Button;

public final class Months extends javax.swing.JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new com.ui.swing.datechooser.Button();
        cmd2 = new com.ui.swing.datechooser.Button();
        cmd3 = new com.ui.swing.datechooser.Button();
        cmd4 = new com.ui.swing.datechooser.Button();
        cmd5 = new com.ui.swing.datechooser.Button();
        cmd6 = new com.ui.swing.datechooser.Button();
        cmd7 = new com.ui.swing.datechooser.Button();
        cmd8 = new com.ui.swing.datechooser.Button();
        cmd9 = new com.ui.swing.datechooser.Button();
        cmd10 = new com.ui.swing.datechooser.Button();
        cmd11 = new com.ui.swing.datechooser.Button();
        cmd12 = new com.ui.swing.datechooser.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setText("1");
        cmd1.setName("1"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setText("2");
        cmd2.setName("2"); // NOI18N
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("3");
        cmd3.setName("3"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("4");
        cmd4.setName("4"); // NOI18N
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("5");
        cmd5.setName("5"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("6");
        cmd6.setName("6"); // NOI18N
        cmd6.setOpaque(true);
        cmd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd6ActionPerformed(evt);
            }
        });
        add(cmd6);

        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("7");
        cmd7.setName("7"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("8");
        cmd8.setName("8"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("9");
        cmd9.setName("9"); // NOI18N
        cmd9.setOpaque(true);
        cmd9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd9ActionPerformed(evt);
            }
        });
        add(cmd9);

        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("10");
        cmd10.setName("10"); // NOI18N
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("11");
        cmd11.setName("11"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("12");
        cmd12.setName("12"); // NOI18N
        cmd12.setOpaque(true);
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    private void cmd6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd6ActionPerformed

    private void cmd9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd9ActionPerformed

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.datechooser.Button cmd1;
    private com.ui.swing.datechooser.Button cmd10;
    private com.ui.swing.datechooser.Button cmd11;
    private com.ui.swing.datechooser.Button cmd12;
    private com.ui.swing.datechooser.Button cmd2;
    private com.ui.swing.datechooser.Button cmd3;
    private com.ui.swing.datechooser.Button cmd4;
    private com.ui.swing.datechooser.Button cmd5;
    private com.ui.swing.datechooser.Button cmd6;
    private com.ui.swing.datechooser.Button cmd7;
    private com.ui.swing.datechooser.Button cmd8;
    private com.ui.swing.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
