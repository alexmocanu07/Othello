package players;

import algorithms.Minimax;
import evaluators.Evaluator;
import evaluators.StaticEvaluator;
import utils.Point;

public class StaticPlayer extends Player {

    private Evaluator evaluator;

    public StaticPlayer(int ID){
        super(ID);
        this.evaluator = new StaticEvaluator();
    }

    @Override
    public String type() {
        return "Static Player";
    }

    @Override
    public Point getMove(int[][] board) {
        return Minimax.solve(board, this.ID, 5, this.evaluator);
    }
}
