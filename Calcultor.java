import javax.swing.*;
import java.awt.*;

public class Calcultor {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.BLACK);

        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setCaretColor(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 8, 8));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        final double[] firstNumber = {0};
        final String[] operator = {""};
        final boolean[] newNumber = {true};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setFocusPainted(false);
            button.setForeground(Color.WHITE);

            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);

            button.addActionListener(e -> {
                String current = display.getText();

                if (text.equals("C")) {
                    display.setText("0");
                    firstNumber[0] = 0;
                    operator[0] = "";
                    newNumber[0] = true;
                }
                else if (text.equals("=")) {
                    double secondNumber = Double.parseDouble(current);
                    double result = 0;

                    switch (operator[0]) {
                        case "+":
                            result = firstNumber[0] + secondNumber;
                            break;
                        case "-":
                            result = firstNumber[0] - secondNumber;
                            break;
                        case "*":
                            result = firstNumber[0] * secondNumber;
                            break;
                        case "/":
                            result = firstNumber[0] / secondNumber;
                            break;
                    }

                    display.setText(String.valueOf(result));
                    operator[0] = "";
                    newNumber[0] = true;
                }
                else if (text.equals("+") || text.equals("-")
                        || text.equals("*") || text.equals("/")) {
                    firstNumber[0] = Double.parseDouble(current);
                    operator[0] = text;
                    newNumber[0] = true;
                }
                else {
                    if (newNumber[0] || current.equals("0")) {
                        display.setText(text);
                        newNumber[0] = false;
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