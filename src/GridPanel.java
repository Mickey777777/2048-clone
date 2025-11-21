import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private static final int GRID_SIZE = 4;
    private static final int TILE_SIZE = 100;
    private static final int MARGIN = 10;

    public GridPanel(){
        int panelSize = GRID_SIZE * (TILE_SIZE + MARGIN) + MARGIN;
        setPreferredSize(new Dimension(panelSize, panelSize));
        setBackground(new Color(153, 139, 124));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);
    }

    private void drawGrid(Graphics2D g2d) {
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                g2d.setColor(new Color(237, 229, 219));
                g2d.fillRoundRect(j*(TILE_SIZE + MARGIN) + MARGIN, i*(TILE_SIZE + MARGIN) + MARGIN, TILE_SIZE, TILE_SIZE, 10, 10);
            }
        }
    }
}
