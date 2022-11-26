package com.ui.swing;

import com.sun.javafx.image.impl.IntArgb;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Table extends JTable {

    private KeyAdapter keyAction;
    private List<Integer> list = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
//                if (i1 == 4) {
//                    header.setHorizontalAlignment(JLabel.CENTER);
//                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {

                Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                setBorder(noFocusBorder);
                com.setForeground(new Color(102, 102, 102));

                if (selected) {
                    com.setBackground(new Color(239, 244, 255));
                } else {
                    com.setBackground(Color.WHITE);
                }

                return com;

            }
        });

    }

    public Table(KeyAdapter keyAction) {
        this.keyAction = keyAction;
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
//                if (i1 == 4) {
//                    header.setHorizontalAlignment(JLabel.CENTER);
//                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {

                Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                setBorder(noFocusBorder);
                com.setForeground(new Color(102, 102, 102));

                if (selected) {
                    com.setBackground(new Color(239, 244, 255));
                } else {
                    com.setBackground(Color.WHITE);
                    for (int j = 0; j < list.size(); j++) {
                        if (i == list.get(j)) {
                            com.setBackground(new Color(230, 255, 204));
                        }
                    }
                    for (int j = 0; j < list2.size(); j++) {
                        if (i == list2.get(j)) {
                            com.setBackground(new Color(234,182,118));
                        }
                    }
                }

                this.addKeyListener(keyAction);
                return com;

            }
        });

    }

    public void addRowColor(int row) {
        list.add(row);
        repaint();
        revalidate();
    }

    public void resetRowColor() {
        list.clear();
    }

    public void removeRowColor(int row) {
        list.remove((Integer) row);
        repaint();
        revalidate();
    }

    public void addRowColor2(int row) {
        list.add(row);
        repaint();
        revalidate();
    }

    public void resetRowColor2() {
        list.clear();
    }

    public void removeRowColor2(int row) {
        list.remove((Integer) row);
        repaint();
        revalidate();
    }

    public void centerCollum(int... cols) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(new Color(255, 255, 255));
        centerRenderer.setFont(new Font("Tohoma", Font.PLAIN, 15));
        centerRenderer.setForeground(new Color(102, 102, 102));

        for (Integer i : cols) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }
    }

    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void addKeyListener(TableCellEditor action, int coll) {
        TableColumn col = this.getColumnModel().getColumn(coll);
        col.setCellEditor(action);
    }
}

class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);

        setBackground(Color.WHITE);
        setFont(new Font("sansserif", 1, 12));
        setForeground(new Color(102, 102, 102));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 230, 230));
        g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
