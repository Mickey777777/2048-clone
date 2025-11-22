
public class AnimatedTileMove{

    private final static int TILE_SIZE = GameConstants.TILE_SIZE;
    private final static int MARGIN = GameConstants.MARGIN;

    private final int fromRow;
    private final int fromCol;
    private final int toRow;
    private final int toCol;
    private final boolean isMerged;
    private final int value;

    public AnimatedTileMove(int fromRow, int fromCol, int toRow, int toCol, boolean isMerged, int value){
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.isMerged = isMerged;
        this.value = value;

    }

    public int getCurrentX(double progress){
        int startX = fromCol * (TILE_SIZE + MARGIN) + MARGIN;
        int endX = toCol * (TILE_SIZE + MARGIN) + MARGIN;
        return (int) (startX + (endX - startX) * progress);
    }

    public int getCurrentY(double progress){
        int startY = fromRow * (TILE_SIZE + MARGIN) + MARGIN;
        int endY = toRow * (TILE_SIZE + MARGIN) + MARGIN;
        return (int) (startY + (endY - startY) * progress);
    }


    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }
    public boolean isMerged() {
        return isMerged;
    }
    public int getValue() {
        return value;
    }
}
