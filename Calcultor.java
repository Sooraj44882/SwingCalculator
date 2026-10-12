import javax.swing.*;
import java.awt.*;

public class Calcultor {

    private static void shuffleButtons(JPanel panel) {
        Component[] components = panel.getComponents();
        java.util.List<Component> shuffled = new java.util.ArrayList<>();
        java.util.Collections.addAll(shuffled, components);
        java.util.Collections.shuffle(shuffled);

        panel.removeAll();
        for (Component component : shuffled) {
            panel.add(component);
        }
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator");
            frame.setSize(400, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            BackgroundPanel background = new BackgroundPanel(null);
            background.setLayout(new BorderLayout(8, 8));

            JTextField display = new JTextField("0");
            display.setEditable(false);
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setFont(new Font("Arial", Font.BOLD, 32));
            display.setOpaque(false);
            display.setForeground(Color.WHITE);
            display.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

            JPanel panel = new JPanel(new GridLayout(4, 4, 8, 8));
            panel.setOpaque(false);
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
            final boolean[] hasJumbled = {false};

            D horrorPanel = new D();

            for (String text : buttons) {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 22));
                button.setFocusPainted(false);
                button.setForeground(Color.WHITE);
                button.setBackground(Color.DARK_GRAY);
                button.setBorderPainted(false);
                button.setOpaque(true);

                button.addActionListener(e -> {
                    SoundPlayer.playClick();

                    String current = display.getText();

                    if (text.equals("C")) {
                        display.setText("0");
                        firstNumber[0] = 0;
                        operator[0] = "";
                        newNumber[0] = true;
                    } else if (text.equals("=")) {
                        try {
                            double secondNumber = Double.parseDouble(current);
                            double result = secondNumber;

                            if (!operator[0].isEmpty()) {
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
                                        if (secondNumber == 0) {
                                            JOptionPane.showMessageDialog(
                                                frame, "Cannot divide by zero."
                                            );
                                            return;
                                        }
                                        result = firstNumber[0] / secondNumber;
                                        break;
                                }
                            }

                          
                            horrorPanel.setCorrectAnswer(result);

                            frame.setContentPane(horrorPanel);
                            frame.revalidate();
                            frame.repaint();

                            if (!hasJumbled[0]) {
                                shuffleButtons(panel);
                                hasJumbled[0] = true;
                            }

                            operator[0] = "";
                            newNumber[0] = true;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(
                                frame, "Please enter a valid calculation."
                            );
                        }
                    } else if (text.equals("+") || text.equals("-")
                            || text.equals("*") || text.equals("/")) {
                        try {
                            firstNumber[0] = Double.parseDouble(current);
                            operator[0] = text;
                            newNumber[0] = true;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(
                                frame, "Please enter a valid number."
                            );
                        }
                    } else {
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

            background.add(display, BorderLayout.NORTH);
            background.add(panel, BorderLayout.CENTER);

            frame.setContentPane(background);
            frame.setVisible(true);

        });
    }
}