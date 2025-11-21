import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameBoard gameBoard = new GameBoard();
        BoardPanel panel = new BoardPanel(gameBoard);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}