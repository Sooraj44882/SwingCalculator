import javax.swing.*;
import java.awt.*;

public class Calcultor {
    public static void main(String[] args) {
        JFrame frame=new JFrame("Calculator");
        JPanel panel=new JPanel();

        JTextField display=new JTextField("0",15);
        display.setEditable(true);
        display.setHorizontalAlignment(JTextField.RIGHT);

        panel.add(display);

        frame.add(panel);

        frame.setSize(600,240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
}