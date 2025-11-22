public class GameStateChecker {
    private static final int GRID_SIZE = GameConstants.GRID_SIZE;

    public static boolean hasEmptyTiles(int[][] grid){
        for(int col=0; col<GRID_SIZE; col++){
            for(int row=0; row<GRID_SIZE; row++){
                if(grid[row][col] == 0) return true;
            }
        }
        return false;
    }

    public static boolean isGameOver(int[][] grid){
        if(hasEmptyTiles(grid)) return false;

        int[] dCol = {0, 1, 0, -1};
        int[] dRow = {-1, 0, 1, 0};

        for(int col=0; col<GRID_SIZE; col++){
            for(int row=0; row<GRID_SIZE; row++){
                for(int d=0; d<4; d++){
                    int nCol = col+dCol[d];
                    int nRow = row+dRow[d];
                    if(nCol<0 || nRow<0 || nCol>=GRID_SIZE || nRow>=GRID_SIZE) continue;
                    if(grid[nRow][nCol] == grid[row][col]) return false;
                }
            }
        }

        return true;
    }
}
