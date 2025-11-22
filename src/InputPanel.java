import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class InputPanel {
    public static void setupKeyListener(JPanel panel, GameBoard board) {
        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        handleMove(board::moveUp, panel, board);
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        handleMove(board::moveDown, panel, board);
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("LEFT");
                        handleMove(board::moveLeft, panel, board);
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        handleMove(board::moveRight, panel, board);
                        break;
                }
            }
        });
    }

    private static void handleMove(Runnable moveAction, JPanel panel, GameBoard board) {
        moveAction.run();
        if(GameStateChecker.isGameOver(board.getGrid())){
            System.out.println("Game Over");
        }
        panel.repaint();
    }
}
