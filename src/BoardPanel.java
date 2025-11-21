import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final GameBoard board;

    public BoardPanel(GameBoard board){
        this.board = board;
        setPreferredSize(new Dimension(GameConstants.PANEL_SIZE, GameConstants.PANEL_SIZE));
        setBackground(new Color(153, 139, 124));

        InputPanel.setupKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);
    }

    private void drawGrid(Graphics2D g2d) {
        int GRID_SIZE = board.getSize();

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                int number = board.getNum(i, j);
                Color color = getTileColor(number);
                g2d.setColor(color);

                int x = j*(GameConstants.TILE_SIZE + GameConstants.MARGIN) + GameConstants.MARGIN;
                int y = i*(GameConstants.TILE_SIZE + GameConstants.MARGIN) + GameConstants.MARGIN;

                g2d.fillRoundRect(x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, 10, 10);

                if(number != 0) drawNumber(g2d, x, y, number);
            }
        }
    }

    private void drawNumber(Graphics2D g2d, int x, int y, int number) {
        if(number <= 4){
            g2d.setColor(new Color(119, 110, 101));
        }else{
            g2d.setColor(new Color(249, 246, 242));
        }
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        String numberString = Integer.toString(number);
        FontMetrics fm = g2d.getFontMetrics();

        int numberX = x + (GameConstants.TILE_SIZE - fm.stringWidth(numberString)) / 2;
        int numberY = y + (GameConstants.TILE_SIZE - fm.getHeight())/2 + fm.getAscent();

        g2d.drawString(numberString, numberX, numberY);
    }

    public static Color getTileColor(int number) {
        return switch (number) {
            case 0 -> new Color(205, 193, 180);
            case 2 -> new Color(238, 228, 218);
            case 4 -> new Color(237, 224, 200);
            case 8 -> new Color(242, 177, 121);
            case 16 -> new Color(245, 149, 99);
            case 32 -> new Color(246, 124, 95);
            case 64 -> new Color(246, 94, 59);
            case 128 -> new Color(237, 207, 114);
            case 256 -> new Color(237, 204, 97);
            case 512 -> new Color(237, 200, 80);
            case 1024 -> new Color(237, 197, 63);
            case 2048 -> new Color(237, 194, 46);
            default -> new Color(60, 58, 50);
        };
    }
}
