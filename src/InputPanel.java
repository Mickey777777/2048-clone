import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class InputPanel {

    private static final int PANEL_SIZE = GameConstants.PANEL_SIZE;

    public static void setupKeyListener(JPanel boardPanel, GameBoard gameBoard, JPanel scorePanel) {
        boardPanel.setFocusable(true);
        boardPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        handleMove(gameBoard::moveUp, boardPanel, gameBoard, scorePanel);
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        handleMove(gameBoard::moveDown, boardPanel, gameBoard, scorePanel);
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("LEFT");
                        handleMove(gameBoard::moveLeft, boardPanel, gameBoard, scorePanel);
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        handleMove(gameBoard::moveRight, boardPanel, gameBoard, scorePanel);
                        break;
                }
            }
        });
    }

    public static void setupMouseListener(JPanel boardPanel, GameBoard gameBoard, JPanel scorePanel) {
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(!GameStateChecker.isGameOver(gameBoard.getGrid())) return;
                int x = e.getX();
                int y = e.getY();

                int boxX = (PANEL_SIZE - 200) / 2;
                int boxY = PANEL_SIZE / 2 + 40;
                int boxWidth = 200;
                int boxHeight = 50;

                if(x>=boxX && x<=boxX+boxWidth && y>=boxY && y<=boxY+boxHeight) {
                    gameBoard.reset();
                    boardPanel.repaint();
                    scorePanel.repaint();
                }
            }
        });
    }

    private static void handleMove(Runnable moveAction, JPanel boardPanel, GameBoard gameBoard, JPanel scorePanel) {
        moveAction.run();
        if(GameStateChecker.isGameOver(gameBoard.getGrid())){
            ((ScorePanel) scorePanel).updateBest(gameBoard.getScore());
            System.out.println("Game Over");
        }
        boardPanel.repaint();
        scorePanel.repaint();
    }
}
