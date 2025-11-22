import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static final int GRID_SIZE = GameConstants.GRID_SIZE;
    private final int[][] grid;
    private List<AnimatedTileMove> lastMoveTiles = new ArrayList<>();

    private int score = 0;

    public GameBoard(){
        grid = new int[GRID_SIZE][GRID_SIZE];
        init();
    }

    public List<AnimatedTileMove> getLastMoveTiles(){
        return lastMoveTiles;
    }

    private void init(){
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile(){
        if(!GameStateChecker.hasEmptyTiles(grid)) return;
        List<Tile> emptyTiles = getEmptyTiles();
        Tile randomTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        int value = Math.random() < 0.9 ? 2 : 4;
        grid[randomTile.getRow()][randomTile.getCol()] = value;
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

    public void reset(){
        score = 0;
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                grid[i][j] = 0;
            }
        }
        init();
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

    private Tile reverseTransform(int row, int col, int rightRotation){ // 회전한 좌표계를 역 연산을 통해 원상태로 복구
        for(int i=0; i<rightRotation; i++){
            int newRow = GRID_SIZE - 1 - col;
            int newCol = row;
            row = newRow;
            col = newCol;
        }
        return new Tile(row, col);
    }

    private boolean move(int rightRotation){
        boolean[][] merged = new boolean[GRID_SIZE][GRID_SIZE];
        lastMoveTiles.clear();
        boolean isMoved = false;
        for(int i=1; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(grid[i][j]!=0){
                    Tile from = reverseTransform(i, j, rightRotation);
                    int value = grid[i][j];
                    int endRow = i;
                    while(endRow>0) {
                        if(grid[endRow-1][j] == grid[endRow][j] && !merged[endRow-1][j]){
                            grid[endRow-1][j] += grid[endRow][j];
                            score += grid[endRow-1][j];
                            grid[endRow][j] = 0;
                            merged[endRow-1][j] = true;
                            isMoved = true;

                            Tile to = reverseTransform(endRow-1, j, rightRotation);
                            lastMoveTiles.add(new AnimatedTileMove(from.getRow(), from.getCol(), to.getRow(), to.getCol(), true, value));
                            break;
                        }else if(grid[endRow-1][j] == 0){
                            grid[endRow-1][j] = grid[endRow][j];
                            grid[endRow][j] = 0;
                            isMoved = true;
                        }else{
                            break;
                        }
                        endRow--;
                    }
                    
                    if(i != endRow && !merged[endRow][j]){ // 이동을 한 경우
                        Tile to = reverseTransform(endRow, j, rightRotation);
                        lastMoveTiles.add(new AnimatedTileMove(from.getRow(), from.getCol(), to.getRow(), to.getCol(), false, value));
                    }
                }
            }
        }
        return isMoved;
    }

    public void moveUp(){
        if(move(0)) addRandomTile();
    }

    public void moveDown(){
        rotateRight();
        rotateRight();
        boolean moved = move(2);
        rotateRight();
        rotateRight();
        if(moved) addRandomTile();
    }

    public void moveLeft(){
        rotateRight();
        boolean moved = move(1);
        rotateRight();
        rotateRight();
        rotateRight();
        if(moved) addRandomTile();
    }

    public void moveRight(){
        rotateLeft();
        boolean moved = move(3);
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

    public int getScore(){
        return score;
    }
}
