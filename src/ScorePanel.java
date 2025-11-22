import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private static final int PANEL_WIDTH = GameConstants.PANEL_SIZE;
    private static final int PANEL_HEIGHT = 80;
    private static final int BOX_WIDTH = 80;
    private static final int BOX_HEIGHT = 50;
    private static final int BOX_MARGIN = 50;

    public ScorePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(250, 248, 241));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // score
        int scoreX = PANEL_WIDTH / 2 - BOX_WIDTH / 2 - BOX_MARGIN;
        int scoreY = PANEL_HEIGHT / 2 - BOX_HEIGHT / 2;
        drawScoreBox(scoreX, scoreY, g2d, new Color(233, 231, 218));

        // best
        int bestX = PANEL_WIDTH / 2 - BOX_WIDTH / 2 + BOX_MARGIN;
        int bestY = PANEL_HEIGHT / 2 - BOX_HEIGHT / 2;
        drawScoreBox(bestX, bestY, g2d, new Color(250, 248, 241));
        drawBoxBorder(bestX, bestY, g2d, new Color(233, 231, 218));

    }

    private void drawScoreBox(int x, int y, Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, BOX_WIDTH, BOX_HEIGHT, 20, 20);
    }

    private void drawBoxBorder(int x, int y, Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(x, y, BOX_WIDTH, BOX_HEIGHT, 20, 20);
    }
}
