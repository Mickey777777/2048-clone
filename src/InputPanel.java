import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class InputPanel {
    public static void setupKeyListener(JPanel panel) {
        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("DOWN");
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("LEFT");
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        break;
                }
            }
        });
    }
}
