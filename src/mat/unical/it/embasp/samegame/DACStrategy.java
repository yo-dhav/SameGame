package mat.unical.it.embasp.samegame;

public class DACStrategy implements AIStrategy {

    @Override
    public int[] getMove(char[][] board) {
        return Dac.getBestMove(board);
    }
}
