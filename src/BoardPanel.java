import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private static final int GRID_SIZE = GameConstants.GRID_SIZE;
    private static final int TILE_SIZE = GameConstants.TILE_SIZE;
    private static final int MARGIN = GameConstants.MARGIN;
    private static final int PANEL_SIZE = GameConstants.PANEL_SIZE;

    private static final int ANIMATION_DURATION = 150;
    private static final int FRAME_DELAY = 16;
    private boolean isAnimating = false;
    private List<AnimatedTileMove> animatedTileMoves = new ArrayList<>();
    private long animationStartTime;
    private Timer animationTimer;
    private double animationProgress = 0;

    private final GameBoard gameBoard;

    public BoardPanel(GameBoard gameBoard, JPanel scorePanel){
        this.gameBoard = gameBoard;
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBackground(new Color(153, 139, 124));

        InputPanel.setupKeyListener(this, gameBoard, scorePanel);
        InputPanel.setupMouseListener(this, gameBoard, scorePanel);
    }

    public void startMoveAnimation(List<AnimatedTileMove> tiles){
        if(tiles == null || tiles.isEmpty()) return;

        this.animatedTileMoves = tiles;
        this.isAnimating = true;
        this.animationStartTime = System.currentTimeMillis();

        animationTimer = new Timer(FRAME_DELAY, e -> {
            long elapsed = System.currentTimeMillis() - animationStartTime;
            animationProgress = Math.min(1.0, (double) elapsed / ANIMATION_DURATION);

            repaint();
            if(animationProgress>=1.0){
                animationTimer.stop();
                isAnimating = false;
                animatedTileMoves = null;
                repaint();
            }
        });

        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isAnimating) {
            drawStaticTiles(g2d);
            drawAnimatingTiles(g2d);
        }else{
            drawGrid(g2d);
        }

        if(GameStateChecker.isGameOver(gameBoard.getGrid())) {
            GameOverOverlay.draw(g2d, gameBoard.getScore());
        }
    }

    private void drawAnimatingTiles(Graphics2D g2d) {
        for(AnimatedTileMove tile : animatedTileMoves){
            int x = tile.getCurrentX(animationProgress);
            int y = tile.getCurrentY(animationProgress);
            int value = tile.getValue();
            boolean isMerged = tile.isMerged();

            if(isMerged && animationProgress>0.8){
                value*=2;
            }

            g2d.setColor(getTileColor(value));
            g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);

            drawNumber(g2d, x, y, value);
        }
    }

    private void drawStaticTiles(Graphics2D g2d) {
        boolean[][] isMoving = new boolean[GRID_SIZE][GRID_SIZE];
        boolean[][] isMergedTarget = new boolean[GRID_SIZE][GRID_SIZE];
        boolean[][] isDestination = new boolean[GRID_SIZE][GRID_SIZE];
        for(AnimatedTileMove tile : gameBoard.getLastMoveTiles()){
            isMoving[tile.getFromRow()][tile.getFromCol()] = true;
            isDestination[tile.getToRow()][tile.getToCol()] = true;
            if(tile.isMerged()) isMergedTarget[tile.getFromRow()][tile.getFromCol()] = true;
        }
        // 배경
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                Color color = getTileColor(0);
                g2d.setColor(color);

                int x = j*(TILE_SIZE + MARGIN) + MARGIN;
                int y = i*(TILE_SIZE + MARGIN) + MARGIN;

                g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);
            }
        }

        // 이동하지 않는 타일
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                int number = gameBoard.getNum(i,j);
                if(number!=0 && !isMoving[i][j] && !isMergedTarget[i][j] && !isDestination[i][j]){
                    int x = j*(TILE_SIZE + MARGIN) + MARGIN;
                    int y = i*(TILE_SIZE + MARGIN) + MARGIN;

                    g2d.setColor(getTileColor(number));
                    g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);
                    drawNumber(g2d, x, y, number);
                }
            }
        }
    }

    private void drawGrid(Graphics2D g2d) {
        int GRID_SIZE = gameBoard.getSize();

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                int number = gameBoard.getNum(i, j);
                Color color = getTileColor(number);
                g2d.setColor(color);

                int x = j*(TILE_SIZE + MARGIN) + MARGIN;
                int y = i*(TILE_SIZE + MARGIN) + MARGIN;

                g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);
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

        int numberX = x + (TILE_SIZE - fm.stringWidth(numberString)) / 2;
        int numberY = y + (TILE_SIZE - fm.getHeight())/2 + fm.getAscent();

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

    public boolean isAnimating() {
        return isAnimating;
    }
}
