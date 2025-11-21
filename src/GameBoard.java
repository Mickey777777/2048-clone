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

    public void rotateRight(){ // 시계방향
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=i; j<GRID_SIZE; j++){
                int tmp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = tmp;
            }
        }

        for(int i=0; i<GRID_SIZE/2; i++){
            for(int j=0; j<GRID_SIZE; j++){
                int tmp = grid[i][j];
                grid[i][j] = grid[GRID_SIZE-i-1][j];
                grid[GRID_SIZE-i-1][j] = tmp;
            }
        }
    }

    public void rotateLeft(){ // 반시계방향
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=i; j<GRID_SIZE; j++){
                int tmp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = tmp;
            }
        }

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE/2; j++){
                int tmp = grid[i][j];
                grid[i][j] = grid[i][GRID_SIZE-j-1];
                grid[i][GRID_SIZE-j-1] = tmp;
            }
        }
    }

    public void moveUp(){
        for(int i=1; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(grid[i][j]!=0){
                    int endY = i;
                    while(endY>0 && grid[endY-1][j]==0) {
                        endY--;
                    }
                    if(endY != i) {
                        grid[endY][j] = grid[i][j];
                        grid[i][j] = 0;
                    }
                }
            }
        }
    }

    public void moveDown(){
        rotateRight();
        rotateRight();
        moveUp();
        rotateRight();
        rotateRight();
    }

    public void moveLeft(){
        rotateLeft();
        moveUp();
        rotateRight();
    }

    public void moveRight(){
        rotateRight();
        moveUp();
        rotateLeft();
    }


    public int getNum(int row , int col){
        return grid[row][col];
    }

    public int getSize(){
        return GRID_SIZE;
    }
}
