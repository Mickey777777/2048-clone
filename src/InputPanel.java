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
                        board.moveUp();
                        panel.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        board.moveDown();
                        panel.repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("LEFT");
                        board.moveLeft();
                        panel.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        board.moveRight();
                        panel.repaint();
                        break;
                }
            }
        });
    }
}
