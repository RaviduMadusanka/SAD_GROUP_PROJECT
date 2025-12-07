package SMMV.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Sheron Randewa
 */
public class customComboBox extends JComboBox {

    private boolean isMouseHover = false;

    public customComboBox() {
        setBorder(new EmptyBorder(8, 10, 7, 10));
        setBackground(new Color(0, 0, 0, 0));
        addItem("Select Data");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseHover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseHover = false;
                repaint();
            }

        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        if (isMouseHover) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        g2.dispose();
    }

}
