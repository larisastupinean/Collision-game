package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private Panel panel = new Panel();
    private JLabel warning = new JLabel();
    private JLabel label = new JLabel("Score:");
    private JLabel scoreLabel = new JLabel("0");
    private JButton resumeButton = new JButton("Resume game");
    private int counter = 0;

    Window() {
        setTitle("Collision game");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(625, 600);
        setLocationRelativeTo(null);
        warning.setBounds(30, 40, 600, 55);
        warning.setFont(new Font("Helvetica", Font.BOLD, 20));
        warning.setForeground(Color.BLUE);
        label.setBounds(30, 25, 80, 20);
        label.setFont(new Font("Helvetica", Font.BOLD, 20));
        scoreLabel.setBounds(100, 25, 600, 20);
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        scoreLabel.setForeground(Color.MAGENTA);
        resumeButton.setBounds(300, 25, 200, 27);
        resumeButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        resumeButton.setForeground(Color.BLUE);
        resumeButton.setVisible(false);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* The counter is used to determine how many times the resume game button is pressed
                in order to limit the number of times that the game can be resumed. At every push
                of the button, the game is resumed and the collision warning and the resume button
                are not visible anymore.
                 */
                counter++;
                Panel panel = new Panel();
                setPanel(panel);
                panel.requestFocus();
                warning.setText(" ");
                resumeButton.setVisible(false);
            }
        });
        add(resumeButton);
        add(scoreLabel);
        add(label);
        add(warning);
        add(panel);
        setVisible(true);
        panel.requestFocus();
    }

    public int getCounter() {
        return counter;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
        add(panel);
    }

    public void resumeButton(boolean button) {
        resumeButton.setVisible(button);
    }

    public void warning(String warning) {
        this.warning.setText(warning);
    }

    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel.setText(scoreLabel);
    }
}
