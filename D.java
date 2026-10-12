
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class D extends BackgroundPanel {
    private JLabel question;
    private JTextField answerField;
    private JButton submitButton;

    private double correctAnswer;
    private boolean answerSet = false;

    public D() {
        super("d/a.jpg");

        setLayout(new BorderLayout(10, 15));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        question = new JLabel(
            "<html><center>TYPE THE FINAL ANSWER<br>"
            + "OF YOUR CALCULATION</center></html>",
            SwingConstants.CENTER
        );
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        question.setForeground(new Color(220, 20, 20));
        question.setFont(new Font("Serif", Font.BOLD, 21));

        answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(300, 40));
        answerField.setFont(new Font("Arial", Font.PLAIN, 18));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerField.setToolTipText("Enter your final answer");

        submitButton = new JButton("SUBMIT");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setFont(new Font("Serif", Font.BOLD, 18));
        submitButton.setForeground(Color.RED);
        submitButton.setBackground(Color.BLACK);
        submitButton.setFocusPainted(false);

        content.add(question);
        content.add(Box.createVerticalStrut(20));
        content.add(answerField);
        content.add(Box.createVerticalStrut(15));
        content.add(submitButton);

        add(content, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            SoundPlayer.playClick();
            checkAnswer();
        });
        answerField.addActionListener(e -> checkAnswer());
    }

    public void setCorrectAnswer(double answer) {
        correctAnswer = answer;
        answerSet = true;

        answerField.setText("");
        answerField.setEnabled(true);
        submitButton.setEnabled(true);

        question.setText(
            "<html><center>TYPE THE FINAL ANSWER<br>"
            + "OF YOUR CALCULATION</center></html>"
        );

        setImage("d/a.jpg");
        SoundPlayer.playBackground();
    }

    private void checkAnswer() {
        if (!answerSet) {
            question.setText("PLEASE CALCULATE FIRST!");
            return;
        }

        String input = answerField.getText().trim();

        if (input.isEmpty()) {
            question.setText("ENTER YOUR ANSWER FIRST!");
            return;
        }

        try {
            double userAnswer = Double.parseDouble(input);

            setImage("d/b.jpeg");
            question.setText(
                "<html><center>YOUR ANSWER HAS BEEN<br>"
                + "RECORDED...</center></html>"
            );

            answerField.setEnabled(false);
            submitButton.setEnabled(false);

            Timer resultTimer = new Timer(1800, e -> {
                if (Math.abs(userAnswer - correctAnswer) < 0.000001) {
                    question.setText(
                        "<html><center>CORRECT ANSWER!<br><br>"
                        + "BUT YOU ARE NOT SAFE...</center></html>"
                    );
                } else {
                    question.setText(
                        "<html><center>WRONG ANSWER!<br><br>"
                        + "YOU HAVE BEEN WARNED...</center></html>"
                    );
                }

                setImage("d/c.jpg");

                Timer videoTimer = new Timer(2000, event -> playVideo());
                videoTimer.setRepeats(false);
                videoTimer.start();
            });

            resultTimer.setRepeats(false);
            resultTimer.start();

        } catch (NumberFormatException ex) {
            question.setText("INVALID ANSWER! TRY A NUMBER.");
        }
    }

    private void playVideo() {
        try {
            File video = new File("d/e.mp4");

            if (video.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(video);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Could not find d/d.mp4 or open a video player."
                );
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Unable to play video: " + ex.getMessage()
            );
        }
    }
}
