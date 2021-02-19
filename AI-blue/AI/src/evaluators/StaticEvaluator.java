package evaluators;

import utils.BoardUtils;
import utils.Point;

import java.util.ArrayList;
import java.util.List;

public class StaticEvaluator implements Evaluator {

    private int otherPlayer;
    private int currentPlayer;

    public int eval(int[][] board, int player){
        otherPlayer = player == 1 ? 2:1;
        currentPlayer = player;
        int pieceCountScore = pieceCount(board, player);
        int cornerCountScore = cornerCount(board, player);
        int possibleMovesCountScore = possibleMovesCount(board, player); // how many moves can be made in the current state
        return 100 * possibleMovesCountScore + 40 * pieceCountScore +1000* cornerCountScore; // random rule for choosing what score element is more important

    }

    public static int pieceCount(int[][] board, int player){
        int otherPlayer = player == 1 ? 2:1;
        int currentPlayerPieces = BoardUtils.getPlayerScore(board, player);
        int otherPlayerPieces = BoardUtils.getPlayerScore(board, otherPlayer);

        return currentPlayerPieces - otherPlayerPieces;
    }

    public static int cornerCount(int[][] board, int player){
        int otherPlayer = player == 1 ? 2:1;
        List<Point> corners = new ArrayList<>();
        corners.add(new Point(0,0));
        corners.add(new Point(0,7));
        corners.add(new Point(7,0));
        corners.add(new Point(7,7));

        int currentPlayerCorners = 0;
        int otherPlayerCorners = 0;

        for(Point corner: corners){
            if(board[corner.getX()][corner.getY()] == player) {
                currentPlayerCorners++;
            }
            else if(board[corner.getX()][corner.getY()] == otherPlayer) {
                otherPlayerCorners++;
            }
        }

        return currentPlayerCorners - otherPlayerCorners;
    }

    public static int possibleMovesCount(int[][] board, int player){
        int otherPlayer = player == 1 ? 2:1;
        int currentPlayerMobility = BoardUtils.getAllPossibleMoves(board, player).size();
        int otherPlayerMobility = BoardUtils.getAllPossibleMoves(board, otherPlayer).size();

        return currentPlayerMobility - otherPlayerMobility;

    }

    public int getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(int otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
