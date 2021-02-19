package players;

import utils.BoardUtils;
import utils.Point;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
    Random random;
    public RandomPlayer(int ID) {
        super(ID);
        this.random = new Random();
    }

    @Override
    public String type() {
        return "Random player";
    }

    @Override
    public Point getMove(int[][] board) {
        List<Point> moves = BoardUtils.getAllPossibleMoves(board, ID);
        if(moves.size() == 0) return null;
        return moves.get(random.nextInt(moves.size()));

    }
}
