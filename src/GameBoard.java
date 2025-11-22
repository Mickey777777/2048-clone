import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static final int GRID_SIZE = GameConstants.GRID_SIZE;
    private final int[][] grid;

    public GameBoard(){
        grid = new int[GRID_SIZE][GRID_SIZE];
        init();
    }

    private void init(){
        addRandomTile();
    }

    private void addRandomTile(){
        if(!GameStateChecker.hasEmptyTiles(grid)) return;
        List<Tile> emptyTiles = getEmptyTiles();
        Tile randomTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        grid[randomTile.getRow()][randomTile.getCol()] = 2;
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

    public void rotateLeft(){ // 반시계방향
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

    public void rotateRight(){ // 시계방향
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

    private boolean move(){
        boolean[][] merged = new boolean[GRID_SIZE][GRID_SIZE];
        boolean isMoved = false;
        for(int i=1; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(grid[i][j]!=0){
                    int endY = i;
                    while(endY>0) {
                        if(grid[endY-1][j] == grid[endY][j] && !merged[endY-1][j]){
                            grid[endY-1][j] += grid[endY][j];
                            grid[endY][j] = 0;
                            merged[endY-1][j] = true;
                            isMoved = true;
                            break;
                        }else if(grid[endY-1][j] == 0){
                            grid[endY-1][j] = grid[endY][j];
                            grid[endY][j] = 0;
                            isMoved = true;
                        }else{
                            break;
                        }
                        endY--;
                    }
                }
            }
        }
        return isMoved;
    }

    public void moveUp(){
        if(move()) addRandomTile();
    }

    public void moveDown(){
        rotateRight();
        rotateRight();
        boolean moved = move();
        rotateRight();
        rotateRight();
        if(moved) addRandomTile();
    }

    public void moveLeft(){
        rotateRight();
        boolean moved = move();
        rotateRight();
        rotateRight();
        rotateRight();
        if(moved) addRandomTile();
    }

    public void moveRight(){
        rotateLeft();
        boolean moved = move();
        rotateLeft();
        rotateLeft();
        rotateLeft();
        if(moved) addRandomTile();
    }


    public int getNum(int row , int col){
        return grid[row][col];
    }

    public int getSize(){
        return GRID_SIZE;
    }

    public int[][] getGrid(){
        return grid;
    }
}
