package SMMV.Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class roundPanelElement extends JPanel {

    private static final int ROUNDED_CORNER = 30;

    public roundPanelElement() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D element = (Graphics2D) g.create();
        element.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        element.setColor(getBackground());
        element.fillRoundRect(0, 0, getWidth(), getHeight(), ROUNDED_CORNER, ROUNDED_CORNER);
        element.dispose();
    }

}
