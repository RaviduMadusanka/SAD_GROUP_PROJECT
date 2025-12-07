/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SMMV.Component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Dilhara
 */
public class Background extends JPanel {

    private static final int ROUND_CORNER_SIZE = 15;

    public Background() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setColor(this.getBackground());
        gd.fillRoundRect(0, 0, getWidth(), getHeight(), ROUND_CORNER_SIZE, ROUND_CORNER_SIZE);
        gd.dispose();
        super.paint(g); 
    }


}
