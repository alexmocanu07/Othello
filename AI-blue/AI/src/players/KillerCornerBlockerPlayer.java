package players;

import algorithms.Minimax;
import evaluators.Evaluator;
import evaluators.WeightedDynamicEvaluator;
import utils.BoardUtils;
import utils.Point;

import java.util.ArrayList;

public class KillerCornerBlockerPlayer extends Player {
    private Evaluator evaluator;
    public KillerCornerBlockerPlayer(int ID){
        super(ID);
        evaluator = new WeightedDynamicEvaluator();
    }

    @Override
    public String type() {
        return "Douchebag Player";
    }

    @Override
    public Point getMove(int[][] board) {
        ArrayList<Point> moves = BoardUtils.getAllPossibleMoves(board,ID);
        int otherPlayer = ((ID == 1) ? 2 : 1);
        Point bestToPlay = null;
        for(Point move: moves){
            if(BoardUtils.isGameFinished(BoardUtils.getNewBoardAfterMove(board, move, ID)))
                bestToPlay = move;
        }
        if(bestToPlay != null){
            System.out.println("[KILLER MOVE] Player " + ID + " found the winning move!");
            return bestToPlay;
        }
        bestToPlay = null;
        int bestValue = Integer.MIN_VALUE;

        //Corner Detection
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(new Point(0,0));
        corners.add(new Point(0,7));
        corners.add(new Point(7,0));
        corners.add(new Point(7,7));
        for(Point move : moves){
            for(Point corner : corners){
                if(corner.equals(move)){
                    int mval = evaluator.eval(BoardUtils.getNewBoardAfterMove(board,move,ID),ID);
                    if(mval > bestValue) {
                        //update best corner
                        bestToPlay = move;
                        bestValue = mval;
                    }
                }
            }
        }
        if(bestToPlay != null){
            System.out.println("[KILLER MOVE] Player " + ID + " found a corner!");
            return bestToPlay;
        }

        bestToPlay = null;
        bestValue = Integer.MIN_VALUE;

        //Blocking Move Detection
        for(Point move : moves){
            int[][] resBoard = BoardUtils.getNewBoardAfterMove(board,move,ID);
            if(BoardUtils.getAllPossibleMoves(resBoard,otherPlayer).size() == 0){ //if opponent has no moves
                int mval = evaluator.eval(resBoard,ID);
                if(mval > bestValue) {
                    bestToPlay = move;
                    bestValue = mval;
                }
            }
        }
        if(bestToPlay != null){

            System.out.println("[KILLER MOVE] Player " + ID + " found a blocking move!");
            return bestToPlay;
        }


        //if no killer moves availiable do a minimax search
        return Minimax.solve(board,ID,6,evaluator);
    }
}
