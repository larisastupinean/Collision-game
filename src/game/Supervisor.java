package game;

import java.awt.*;

public class Supervisor extends Thread {
    private final Window win;
    private int score = 0;

    Supervisor(Window win) {
        this.win = win;
    }

    @Override
    public void run() {
        while (true) {
            Panel panel = this.win.getPanel();
            // Collision detection.
            Rectangle circle = new Rectangle(panel.getxCircle(), panel.getyCircle(), panel.getWidthCircle(), panel.getWidthCircle());
            for (SquareThread s : panel.getSq()) {
                /* The final score is computed by adding the number of squares which were successfully avoided.
                This score is kept after each resuming of the game, the number of newly avoided squares being
                added to it.
                 */
                this.score += s.getScore();
                s.setScore(0);
                win.setScoreLabel(String.valueOf(score));
                Rectangle square = s.getR();
                if (square.intersects(circle)) {
                    /* If a collision between the circle and a square is detected, the circle cannot be
                    moved anymore and the threads responsible with the movement of the squares are stopped.
                     */
                    panel.setPlay(false);
                    for (SquareThread st : panel.getSq())
                        st.stop();
                    // The game can only be be resumed 3 times.
                    if (win.getCounter() < 3) {
                        /* At each collision a warning is displayed in the window and the resume game
                        button is made visible.
                         */
                        win.warning("Collision detected!");
                        win.resumeButton(true);
                    } else
                        /* After resuming the game 3 times, at the next detected collision another warning
                        is displayed, this time also announcing that the game is over.
                         */
                        win.warning("Collision detected! Game over!");
                }
            }
        }
    }
}
