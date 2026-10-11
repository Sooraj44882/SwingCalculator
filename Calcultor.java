import javax.swing.*;
import java.awt.*;

public class Calcultor {
    public static void main(String[] args) {
        JFrame frame=new JFrame("Calculator");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(5, 5));


        JTextField display=new JTextField("0",15);
        display.setEditable(true);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 3, 5, 5));
         
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            button.addActionListener(e -> {
                String current = display.getText();

                if (text.equals("C")) {
                    display.setText("0");
                } else if (text.equals("=")) {
                    display.setText(current);
                } else if (text.equals("+") ||
                        text.equals("-") ||
                        text.equals("*") ||
                        text.equals("/")) {
                    
                    display.setText(current + " " + text + " ");
                } else {
                    if (current.equals("0")) {
                        display.setText(text);
                    } else {
                        display.setText(current + text);
                    }
                }
            });

            panel.add(button);
        }


        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    
}