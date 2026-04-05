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
        return switch (mode) {
            case 0 -> new GreedyStrategy();
            case 1 -> new BackTracking();
            case 2 -> new DivideConquer();
            case 3 -> new RandomStrategy();
            default -> new RandomStrategy();
        };
    }
}
