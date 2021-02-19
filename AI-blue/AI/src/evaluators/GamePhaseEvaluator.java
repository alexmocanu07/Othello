package evaluators;

import utils.BoardUtils;

public class GamePhaseEvaluator implements Evaluator {

    private final int EARLY_GAME = 1;
    private final int MID_GAME = 2;
    private final int LATE_GAME = 3;

    public int eval(int[][] board, int player){
        int gameTime = getGameTime(board);
        if(gameTime == EARLY_GAME){
            return (1000 * StaticEvaluator.cornerCount(board, player) + 70 * StaticEvaluator.possibleMovesCount(board, player) + 10 *StaticEvaluator.pieceCount(board, player));
        }
        if(gameTime == MID_GAME){
            return (700 * StaticEvaluator.cornerCount(board, player) + 100 * StaticEvaluator.possibleMovesCount(board, player) + 40 * StaticEvaluator.pieceCount(board, player));
        }
        else{
            return (400 * StaticEvaluator.cornerCount(board, player) + 250 * StaticEvaluator.possibleMovesCount(board, player) + 100 * StaticEvaluator.pieceCount(board, player));
        }
    }

    public int getGameTime(int[][]board){
        int piecesCount = BoardUtils.getTotalScore(board);

        if(piecesCount < 25) return EARLY_GAME;
        if(piecesCount < 50) return MID_GAME;
        return LATE_GAME;
    }


}
