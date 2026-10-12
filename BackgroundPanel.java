
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image image;

    public BackgroundPanel(String path) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setOpaque(true);

        if (path != null) {
            image = new ImageIcon(path).getImage();
        }
    }

    public void setImage(String path) {
        image = new ImageIcon(path).getImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
