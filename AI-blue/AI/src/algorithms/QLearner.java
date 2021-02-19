package algorithms;

import utils.BoardUtils;
import utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QLearner {
    private Network network;

    private final int networkInputNeurons = 64;
    private final int networkHiddenNeurons = 50;
    private final int networkOutputNeurons = 1;
    private final int networkDiscountFactor = 1;
    private final double networkLearnRate = 0.001;

    private int epochCount;
    private double learnRate;
    private double discountFactor;

    private Random random;

    private int[][] board;
    private int currentPlayer;

    private double[][] rewards;

    public QLearner(int epochCount, double learnRate, double discountFactor){

        random = new Random();

        this.epochCount = epochCount;
        this.learnRate = learnRate;
        this.discountFactor = discountFactor;
        this.network = new Network(networkInputNeurons, networkHiddenNeurons, networkOutputNeurons, epochCount, networkLearnRate);


    }
    private void initializeGame(){
        board = new int[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) board[i][j] = 0;
        board[3][3] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        board[4][4] = 1;
        currentPlayer = random.nextInt(100) % 2;
        currentPlayer++;

    }
    public void train(int epochCount, double learnRate, double discountFactor){
        for(int i = 0; i < epochCount; i++){
            initializeGame();
            List<List<Point>> moves = new ArrayList<>();
            moves.add(new ArrayList<>());
            moves.add(new ArrayList<>());
            while(notOver()){
                Point bestMove = null;
                List<Point> possibleMoves = BoardUtils.getAllPossibleMoves(board, currentPlayer);
                if(possibleMoves.size() == 0){
                    currentPlayer = currentPlayer == 1 ? 2: 1;
                    continue;
                }
                int exploration = random.nextInt(100);
                if(exploration < 10){
                    bestMove = possibleMoves.get(random.nextInt(possibleMoves.size()));
                }
                else{
                    double bestReward = Integer.MIN_VALUE;
                    for(Point move: possibleMoves)
                        if(rewards[move.getX()][move.getY()] > bestReward){
                            bestReward = rewards[move.getX()][move.getY()];
                            bestMove = move;
                        }
                }
                if(bestMove != null)moves.get(currentPlayer - 1).add(bestMove);
                this.board = BoardUtils.getNewBoardAfterMove(board,bestMove,currentPlayer);
                currentPlayer = currentPlayer == 1 ? 2:1;

            }
            int player1Score = BoardUtils.getPlayerScore(board, 1);
            int player2Score = BoardUtils.getPlayerScore(board, 2);
            int winner = player1Score > player2Score ? 1: player1Score == player2Score ? 0: 2;
            List<Point> winnerMoves;
            if(winner == 0){
                Random random = new Random();
                winnerMoves = moves.get(random.nextInt(2));
            }
            else {
                winnerMoves = moves.get(winner - 1);
            }
            updateRewardMatrix(winnerMoves);
        }
    }

    private void updateRewardMatrix(List<Point> winnerMoves){

    }

    private boolean notOver() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(board[i][j] == 0) return true;
        return false;
    }

    public int[] convertBoardToArray(int[][] board, int currentPlayer){
        int[] result = new int[64];
        if(currentPlayer == 1) {
            for (int i = 0; i < 64; i++) result[i] = board[i / 8][i % 8] == 1 ? 1: board[i/8][i%8] == 0 ? 0: -1;
        }
        else{
            for (int i = 0; i < 64; i++) result[i] = board[i / 8][i % 8] == 2 ? 1: board[i/8][i%8] == 0 ? 0: -1;
        }

        return result;
    }
}
