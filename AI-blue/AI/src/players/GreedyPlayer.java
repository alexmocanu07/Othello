package players;

import utils.BoardUtils;
import utils.Point;

import java.util.List;


public class GreedyPlayer extends Player {
    public GreedyPlayer(int ID) {
        super(ID);
    }

    @Override
    public String type() {
        return "Greedy Player";
    }

    @Override
    public Point getMove(int[][] board) {
        Point bestMove = null;
        int bestScore = 0;
        List<Point> moves = BoardUtils.getAllPossibleMoves(board, ID);
        if(moves.size() == 0) return null;
        for(Point move: moves){
            int reversedTiles = BoardUtils.getReversedPieces(board,ID, move.getX(), move.getY()).size(); // how many enemy pieces can I overtake
            if(reversedTiles > bestScore){
                bestMove = move;
                bestScore = reversedTiles;
            }

        }
        return bestMove;
    }
}
