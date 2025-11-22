import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class InputPanel {
    public static void setupKeyListener(JPanel boardPanel, GameBoard board, JPanel scorePanel) {
        boardPanel.setFocusable(true);
        boardPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        handleMove(board::moveUp, boardPanel, board, scorePanel);
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        handleMove(board::moveDown, boardPanel, board, scorePanel);
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("LEFT");
                        handleMove(board::moveLeft, boardPanel, board, scorePanel);
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        handleMove(board::moveRight, boardPanel, board, scorePanel);
                        break;
                }
            }
        });
    }

    private static void handleMove(Runnable moveAction, JPanel panel, GameBoard board, JPanel scorePanel) {
        moveAction.run();
        if(GameStateChecker.isGameOver(board.getGrid())){
            System.out.println("Game Over");
        }
        panel.repaint();
        scorePanel.repaint();
    }
}
