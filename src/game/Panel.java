package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener, KeyListener {
    private ArrayList<SquareThread> sq = new ArrayList<>();
    private int xCircle;
    private int yCircle;
    private int widthCircle;
    private int speed = 0;
    private boolean play;

    Panel() {
        new Timer(5, this).start();
        addKeyListener(this);
        setFocusable(true);
        init();
        setBounds(5, 90, 600, 465);
        setBackground(Color.PINK);
    }

    public void init() {
        this.play = true;
        this.xCircle = 290;
        this.yCircle = 420;
        this.widthCircle = 35;
        for (int i = 0; i < 3; i++) {
            SquareThread s = new SquareThread(this, new Rectangle(200 * i + 50, 50, 100, 100));
            sq.add(s);
            s.start();
        }
    }

    public int getxCircle() {
        return xCircle;
    }

    public int getyCircle() {
        return yCircle;
    }

    public int getWidthCircle() {
        return widthCircle;
    }

    public ArrayList<SquareThread> getSq() {
        return sq;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            Rectangle r = sq.get(i).getR();
            g.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        }
        g.setColor(Color.MAGENTA);
        g.fillOval(xCircle, yCircle, widthCircle, widthCircle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.play) {
            if (xCircle < 5) {
                xCircle = 5;
                speed = 0;
            }
            if (xCircle > 555) {
                xCircle = 555;
                speed = 0;
            }
            xCircle = xCircle + speed;
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT) {
            speed = -2;
        }

        if (code == KeyEvent.VK_RIGHT) {
            speed = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        speed = 0;
    }
}
