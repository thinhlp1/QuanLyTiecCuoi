package com.ui.swing.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

public class ChartPie extends javax.swing.JPanel {

    public List<ModelChartPie> getModel() {
        return model;
    }

    public void setModel(List<ModelChartPie> model, String nam) {
        if (nam.equals("2021")) {
            System.out.println("");
        }
        this.model = model;
        initData(nam);
    }

    private List<ModelChartPie> model;

    public ChartPie() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    private void initData(String nam) {
         if (nam.equals("2021")) {
            System.out.println("");
        }
        panelChartPie1.removeAllData();
        panelData.removeAll();
        if (model != null) {
            for (ModelChartPie data : model) {
                panelChartPie1.addItem(data);
                panelData.add(new ItemChartPie(data));
            }
        }
    }

    public void removeAllData() {
        if (model != null) {
            if (!model.isEmpty()) {
                model.clear();
                repaint();
            }
        } else {

        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChartPie1 = new com.ui.swing.chart.PanelChartPie();
        panelData = new javax.swing.JPanel();

        javax.swing.GroupLayout panelChartPie1Layout = new javax.swing.GroupLayout(panelChartPie1);
        panelChartPie1.setLayout(panelChartPie1Layout);
        panelChartPie1Layout.setHorizontalGroup(
            panelChartPie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        panelChartPie1Layout.setVerticalGroup(
            panelChartPie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        panelData.setOpaque(false);
        panelData.setLayout(new javax.swing.BoxLayout(panelData, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChartPie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChartPie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.chart.PanelChartPie panelChartPie1;
    private javax.swing.JPanel panelData;
    // End of variables declaration//GEN-END:variables
}
