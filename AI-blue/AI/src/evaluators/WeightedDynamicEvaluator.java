package evaluators;

import utils.BoardUtils;

public class WeightedDynamicEvaluator implements Evaluator {

    private static int[][] tileScores = {
            {100, -25, 10, 5, 5, 10, -25, 100},
            {-25, -25, 2, 2, 2, 2, -25, -25},
            {10, 2, 5, 1, 1, 5, 2, 10},
            {5, 2, 1, 2, 2, 1, 2, 5},
            {5, 2, 1, 2, 2, 1, 2, 5},
            {10, 2, 5, 1, 1, 5, 2, 10},
            {-25, -25, 2, 2, 2, 2, -25, -25},
            {100, -25, 10, 5, 5, 10, -25, 100}
    };

    private static int[][] weights = {
            {8, -40, 10, 210, 520}, // 55
            {33, -15, 4, 416, 2153}, // 56
            {46, -1, 3, 612, 4141}, // 57
            {51, 62, 3, 595, 3184}, // 58
            {33, 66, 2, 384, 2777}, // 59
            {44, 163, 0, 443, 2568}, //60
            {13, 66, 0, 121, 986}, //61
            {4, 31, 0, 27, 192}, //62
            {8, 77, 0, 36, 299} //63
    };

    private int[][] tileCountWeights;
    @Override
    public int eval(int[][] board, int player) {
        int score = 0;
        int[] currentWeights = tileCountWeights[BoardUtils.getTotalScore(board)];
        score += currentWeights[0] * StaticEvaluator.possibleMovesCount(board, player); // how many moves can be made in the current state
        score += currentWeights[1] * StaticEvaluator.pieceCount(board, player); // diff of pieces owned by players
        score += currentWeights[2] * positionScore(board, player); // based on {tileScores} !!!
        score += currentWeights[3] * ireversibleTilesScore(board, player); // pieces that are forever owned by a player (e.g. lines that touch an edge)
        score += currentWeights[4] * StaticEvaluator.cornerCount(board, player); // pieces/lines that touch a corner

        return score;
    }

    public WeightedDynamicEvaluator(){
        tileCountWeights = new int[65][5];

        for(int tileCount = 0; tileCount <65; tileCount++){
            if(tileCount <56 || tileCount == 64) {
                tileCountWeights[tileCount] = weights[0];
                continue;
            }
            tileCountWeights[tileCount] = weights[tileCount - 55];
        }
    }


    public static int positionScore(int[][] board, int player){
        int otherPlayer = player == 1 ? 2:1;
        int currentPlayerScore = 0;
        int otherPlayerScore = 0;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(board[i][j] == player) currentPlayerScore += tileScores[i][j];
                else if(board[i][j] == otherPlayer) otherPlayerScore += tileScores[i][j];

        return currentPlayerScore - otherPlayerScore;
    }


    public static int ireversibleTilesScore(int[][] board, int player){
        int otherPlayer = (player==1) ? 2 : 1;

        int currentPlayerScore = 0;
        int otherPlayerScore = 0;

        if(board[0][0] == player) currentPlayerScore += BoardUtils.getIrreversibleTiles(board,player,0,0).size();
        if(board[0][7] == player) currentPlayerScore += BoardUtils.getIrreversibleTiles(board,player,0,7).size();
        if(board[7][0] == player) currentPlayerScore += BoardUtils.getIrreversibleTiles(board,player,7,0).size();
        if(board[7][7] == player) currentPlayerScore += BoardUtils.getIrreversibleTiles(board,player,7,7).size();

        if(board[0][0] == otherPlayer) otherPlayerScore += BoardUtils.getIrreversibleTiles(board,otherPlayer,0,0).size();
        if(board[0][7] == otherPlayer) otherPlayerScore += BoardUtils.getIrreversibleTiles(board,otherPlayer,0,7).size();
        if(board[7][0] == otherPlayer) otherPlayerScore += BoardUtils.getIrreversibleTiles(board,otherPlayer,7,0).size();
        if(board[7][7] == otherPlayer) otherPlayerScore += BoardUtils.getIrreversibleTiles(board,otherPlayer,7,7).size();

        return 100 * (currentPlayerScore - otherPlayerScore) / (currentPlayerScore + otherPlayerScore + 1);
    }
}
