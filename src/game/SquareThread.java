package game;

import java.awt.*;

public class SquareThread implements Runnable {
    private Panel panel;
    private Rectangle r;
    private int speed;
    private int minSpeed = 1;
    private int maxSpeed = 5;
    private boolean play = true;
    private int score = 0;


    SquareThread(Panel panel, Rectangle r) {
        this.panel = panel;
        this.r = r;
        this.speed = (int) (Math.random() * (maxSpeed - minSpeed) + minSpeed);
    }

    public Rectangle getR() {
        return r;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void moveSquare() {
        if (r.getY() < 500)
            this.r.translate(0, speed);
        else {
            this.r.setLocation((int) this.r.getX(), 0);
            // This intermediary score variable becomes 1 only if a square is successfully avoided.
            score = 1;
        }
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.play = false;
    }

    @Override
    public void run() {
        while (this.play) {
            this.moveSquare();
            panel.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
