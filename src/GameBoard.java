public class GameBoard {
    private static final int GRID_SIZE = 4;
    private int[][] grid;

    public GameBoard(){
        grid = new int[GRID_SIZE][GRID_SIZE];
        Init();
    }

    private void Init(){
        grid[0][0] = 0;
        grid[0][1] = 2;
        grid[0][2] = 4;
        grid[0][3] = 8;
        grid[1][0] = 16;
        grid[1][1] = 32;
        grid[1][2] = 64;
        grid[1][3] = 128;
    }

    public int getNum(int row , int col){
        return grid[row][col];
    }

    public int getSize(){
        return GRID_SIZE;
    }
}
