package algorithms;

import evaluators.Evaluator;
import utils.BoardUtils;
import utils.Point;

public class Minimax {

    public static Point solve(int[][] board, int player, int depth, Evaluator e){

        int bestScore = Integer.MIN_VALUE;
        Point bestMove = null;
        for(Point move : BoardUtils.getAllPossibleMoves(board,player)){
            int[][] newBoard = BoardUtils.getNewBoardAfterMove(board,move,player);
            int newScore = minimax(newBoard,player,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE,e);
            if(newScore > bestScore) {
                bestScore = newScore;
                bestMove = move;
            }
        }
        return bestMove;
    }


    private static int minimax(int[][] board, int player, int depth, boolean max, int alpha, int beta, Evaluator e){

        if(depth == 0 || BoardUtils.isGameFinished(board)){
            return e.eval(board,player);
        }
        int otherPlayer = (player==1) ? 2 : 1;

        if((max && !BoardUtils.hasAnyMoves(board,player)) || (!max && !BoardUtils.hasAnyMoves(board,otherPlayer))){
            // daca playerul curent nu are mutari in configuratia curenta
            return minimax(board,player,depth-1,!max,alpha,beta,e);
        }
        int score;
        if(max){
            score = Integer.MIN_VALUE;
            for(Point move : BoardUtils.getAllPossibleMoves(board,player)){
                int[][] newBoard = BoardUtils.getNewBoardAfterMove(board,move,player);
                int newScore = minimax(newBoard,player,depth-1,false,alpha,beta,e);
                score = Math.max(score, newScore);

                alpha = Math.max(score, alpha);
                if(beta <= alpha) break;
            }
        }else{

            score = Integer.MAX_VALUE;
            for(Point move : BoardUtils.getAllPossibleMoves(board,otherPlayer)){ //opponent turn
                int[][] newBoard = BoardUtils.getNewBoardAfterMove(board,move,otherPlayer);
                int newscore = minimax(newBoard,player,depth-1,true,alpha,beta,e);
                score = Math.min(newscore, score);

                beta = Math.min(score, beta);
                if(beta <= alpha) break;
            }
        }
        return score;
    }

}
