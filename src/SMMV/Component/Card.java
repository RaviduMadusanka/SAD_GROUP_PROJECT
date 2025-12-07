package SMMV.Component;

import SMMV.Connection.connection_ol;
import lk.chandula.model.Model_Card;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.ResultSet;

public class Card extends javax.swing.JPanel {
    
    public Color getColor1() {
        return color1;
    }
    
    public void setColor1(Color color1) {
        this.color1 = color1;
    }
    
    public Color getColor2() {
        return color2;
    }
    
    public void setColor2(Color color2) {
        this.color2 = color2;
    }
    
    private Color color1;
    private Color color2;
    
    public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
        report1();
    }
    
    public void setData(Model_Card data) {
        lbIcon.setIcon(data.getIcon());
        lbTitle.setText(data.getTitle());
        lbValues.setText(data.getValues());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        lbValues = new javax.swing.JLabel();

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/chandula/image/stock.png"))); // NOI18N

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Stock Total");

        lbValues.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        lbValues.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbValues, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(lbValues)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }
    
    private void report1() {
        
        try {
            
            double total = 0;
            ResultSet stockResult = connection_ol.search("SELECT * FROM `stock`");
            while (stockResult.next()) {                
                
                int qty = Integer.parseInt(stockResult.getString("qty"));
                double selling_price = stockResult.getDouble("selling_price");
                
                double item_total = qty * selling_price;
                total += item_total;
                
            }
            lbValues.setText("RS : " +String.valueOf( total)+"0");
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValues;
    // End of variables declaration//GEN-END:variables
}
