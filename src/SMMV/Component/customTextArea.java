package SMMV.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class customTextArea extends JTextField {

    private boolean isMouseOver = false;

    public customTextArea() {
        setBorder(new EmptyBorder(8, 10, 7, 10));
        setBackground(new Color(0,0,0,0));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                repaint();
            }

        });
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (isMouseOver) {
            g2.setColor(Color.WHITE);
        }else{
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        g2.dispose();
    }

}
