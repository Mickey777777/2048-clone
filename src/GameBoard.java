import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static final int GRID_SIZE = GameConstants.GRID_SIZE;
    private int[][] grid;

    public GameBoard(){
        grid = new int[GRID_SIZE][GRID_SIZE];
        init();
    }

    private void init(){
        addRandomTile();
    }

    private void addRandomTile(){
        List<Tile> emptyTiles = getEmptyTiles();

        Tile randomTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        grid[randomTile.getCol()][randomTile.getRow()] = 2;
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTiles = new ArrayList<>();

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(grid[i][j] == 0) emptyTiles.add(new Tile(i, j));
            }
        }

        return emptyTiles;
    }

    public int getNum(int row , int col){
        return grid[row][col];
    }

    public int getSize(){
        return GRID_SIZE;
    }
}
