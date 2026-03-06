package mat.unical.it.embasp.samegame;

import java.util.ArrayList;
import java.util.List;

public class Dac {
    // Time complexity : O(N^2) space complexity : O(N)
    
    public static int[] getBestMove(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        int bestScore = -1;
        int[] bestMove = null;

        boolean[][] visited = new boolean[rows][cols];

        // DIVIDE: find all blocks
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (board[i][j] == '0' || visited[i][j])
                    continue;

                List<int[]> block =
                        GameEngine.findBlock(board, i, j, board[i][j]);

                // mark visited
                for (int[] cell : block) {
                    visited[cell[0]][cell[1]] = true;
                }

                if (block.size() < 2)
                    continue;

                // CONQUER: evaluate this block
                int score = evaluateMove(board, block);

                // COMBINE: choose max
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = new int[]{i, j};
                }
            }
        }
        return bestMove;
    }

    /**
     * Evaluate a single block by simulating the move
     */
    private static int evaluateMove(char[][] board, List<int[]> block) {

        char[][] cloned = cloneBoard(board);

        // remove block
        for (int[] cell : block) {
            cloned[cell[0]][cell[1]] = '0';
        }

        GameEngine.applyGravity(cloned);

        // scoring rule
        int size = block.size();
        return (int) Math.pow(size - 1, 2);
    }

    /**
     * Utility: deep clone board
     */
    private static char[][] cloneBoard(char[][] board) {
        char[][] copy = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
        }
        return copy;
    }
}