package players;

import algorithms.Minimax;
import evaluators.Evaluator;
import evaluators.WeightedDynamicEvaluator;
import utils.Point;

public class WeightedDynamicPlayer extends Player {
    Evaluator evaluator;
    public WeightedDynamicPlayer(int ID) {
        super(ID);
        evaluator = new WeightedDynamicEvaluator();
    }

    @Override
    public String type() {
        return "Dynamic Player";
    }

    @Override
    public Point getMove(int[][] board) {
        return Minimax.solve(board, ID, 6, evaluator);
    }
}
