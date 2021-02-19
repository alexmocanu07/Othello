package players;

import algorithms.Minimax;
import evaluators.Evaluator;
import evaluators.GamePhaseEvaluator;
import utils.Point;

public class GamePhasePlayer extends Player {

    private Evaluator evaluator;
    public GamePhasePlayer(int ID){
        super(ID);
        this.evaluator = new GamePhaseEvaluator();
    }

    @Override
    public String type() {
        return "GamePhase Player";
    }

    @Override
    public Point getMove(int[][] board) {
        return Minimax.solve(board,ID,6,evaluator);
    }
}
