import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameBoard gameBoard = new GameBoard();
        ScorePanel scorePanel = new ScorePanel(gameBoard);
        BoardPanel boardPanel = new BoardPanel(gameBoard, scorePanel);

        frame.setLayout(new BorderLayout());
        frame.add(scorePanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boardPanel.requestFocusInWindow();
    }
}