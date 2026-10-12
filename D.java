import javax.swing.*;
import java.awt.*;

public class D extends JPanel {

    public D() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JLabel horrorText = new JLabel(
            "<html><center>"
            + "YOU THINK YOU KNOW THE ANSWER?"
            + "<br>TELL ME... WHAT IS YOUR ANSWER?"
            + "</center></html>",
            SwingConstants.CENTER
        );

        horrorText.setForeground(Color.RED);
        horrorText.setFont(new Font("Arial", Font.BOLD, 16));

        add(horrorText, BorderLayout.CENTER);
    }
}
