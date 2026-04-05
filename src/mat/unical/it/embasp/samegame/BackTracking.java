package mat.unical.it.embasp.samegame;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BackTracking implements AIStrategy {
    private static final int MAX_DEPTH = 2;
    @Override
    public int[] getMove(char[][] board) {
        List<int[]> moves = GameEngine.getAllMoves(board);
        if (moves.isEmpty()) return null;
        orderMovesDescending(moves);

        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = null;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int[] move : moves) {

            char[][] cloned = GameEngine.copyBoard(board);
            GameEngine.simulateMove(cloned, move[0], move[1]);

            int value = move[2] + minimax(cloned, 1, false, alpha, beta);

            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }

            alpha = Math.max(alpha, bestValue);
        }

        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing, int alpha, int beta) {
        if (GameEngine.isGameOver(board) || depth >= MAX_DEPTH) {
            return heuristic(board);
        }
        List<int[]> moves = GameEngine.getAllMoves(board);
        orderMovesDescending(moves);
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] move : moves) {
                char[][] cloned = GameEngine.copyBoard(board);
                GameEngine.simulateMove(cloned, move[0], move[1]);
                int eval = move[2] + minimax(cloned, depth + 1, false, alpha, beta);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                    break; 
            }
            return maxEval; 

        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] move : moves) {
                char[][] cloned = GameEngine.copyBoard(board);
                GameEngine.simulateMove(cloned, move[0], move[1]);
                int eval = minimax(cloned, depth + 1, true, alpha, beta)
                           - move[2];
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                    break; 
            }
            return minEval;
        }
    }

    private int heuristic(char[][] board) {
        List<int[]> moves = GameEngine.getAllMoves(board);
        int heuristicScore = 0;
        for (int[] move : moves) {
            heuristicScore += move[2]; 
        }
        return heuristicScore;
    }

    private void orderMovesDescending(List<int[]> moves) {
        Collections.sort(moves, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(b[2], a[2]);
            }
        });
    }
}