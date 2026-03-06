package mat.unical.it.embasp.samegame;
public class AIPlayer {
    public void clearMemo() {

    }

    public int[] getMove(int mode, char[][] board) {
        AIStrategy strategy = getStrategy(mode);
        if (strategy != null) {
            return strategy.getMove(board);
        }
        return new RandomStrategy().getMove(board); // Fallback
    }

    private AIStrategy getStrategy(int mode ) {
        switch (mode) {
            case 0: return new GreedyStrategy();
            case 1: return new DACStrategy(); 
            case 2: return new BackTrackStratergyOPP();
            case 3: return new UKDAC();
            case 4: return new RandomStrategy(); 
            default: return new RandomStrategy(); 
        }
    }
}
