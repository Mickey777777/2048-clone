import java.awt.*;

public class GameOverOverlay {
    private static final int PANEL_SIZE = GameConstants.PANEL_SIZE;
    private static final int PLAY_AGAIN_BOX_WIDTH = 200;
    private static final int PLAY_AGAIN_BOX_HEIGHT = 50;

    public static void draw(Graphics2D g2d, int score) {
        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.fillRect(0, 0, PANEL_SIZE, PANEL_SIZE);

        // Game Over!
        g2d.setColor(new Color(119, 110, 101));
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        String gameOverText = "Game Over!";
        FontMetrics fm = g2d.getFontMetrics();
        int textX = (PANEL_SIZE - fm.stringWidth(gameOverText)) / 2;
        int textY = PANEL_SIZE / 2 - 20 - 50;
        g2d.drawString(gameOverText, textX, textY);

        // Score:
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String scoreText = "Score: " + score;
        fm = g2d.getFontMetrics();
        int scoreX = (PANEL_SIZE - fm.stringWidth(scoreText)) / 2;
        int scoreY = PANEL_SIZE / 2 + 30 - 50;
        g2d.drawString(scoreText, scoreX, scoreY);

        // Play Again Box
        int playAgainBoxX = (PANEL_SIZE - PLAY_AGAIN_BOX_WIDTH) / 2;
        int playAgainBoxY = PANEL_SIZE / 2 + 40;
        g2d.setColor(new Color(143, 122, 102));
        g2d.fillRoundRect(playAgainBoxX, playAgainBoxY, PLAY_AGAIN_BOX_WIDTH, PLAY_AGAIN_BOX_HEIGHT, 30, 30);

        // Play Again 텍스트 (박스 중앙에 배치)
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String playAgainText = "Play Again";
        fm = g2d.getFontMetrics();
        int playAgainX = playAgainBoxX + (PLAY_AGAIN_BOX_WIDTH - fm.stringWidth(playAgainText)) / 2;
        int playAgainY = playAgainBoxY + (PLAY_AGAIN_BOX_HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(playAgainText, playAgainX, playAgainY);
    }
}
