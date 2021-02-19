package game;

import UI.GameWindow;
import players.Player;
import utils.BoardUtils;
import utils.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Game {


    private GameWindow gameWindow;

    int turn = 1;
    int player1Score;
    int player2Score;

    private boolean awaitForClick = false;
    int[][] board;

    Player player1, player2;
    Timer player1HandlerTimer;
    Timer player2HandlerTimer;

    private boolean forceQuit = false;

    public Game() {

    }

    public void start(){

        this.turn = 1;
        this.forceQuit = false;
        this.awaitForClick = false;
        this.player1Score = 0;
        this.player2Score = 0;
        board = new int[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) board[i][j] = 0;

        resetBoard();
        player1HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player1);
            player1HandlerTimer.stop();
            manageTurn();
        });

        player2HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player2);
            player2HandlerTimer.stop();
            manageTurn();
        });
        manageTurn();
    }

    public void updateBoardInfo(){

        player1Score = 0;
        player2Score = 0;
        gameWindow.getGameInterface().resetHighlights();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) player1Score++;
                if (board[i][j] == 2) player2Score++;

                if (BoardUtils.canPlay(board, turn, i, j)) {
                    gameWindow.highlight(i, j);
                }
            }
        }
        gameWindow.getGameInterface().setPlayerText(player1, player2);
    }
    public void handleAI(Player player){
        if(player == null) return;
        Point move = player.getMove(board);
        if(!BoardUtils.canPlay(board,player.getID(),move.getX(), move.getY())) System.err.println("[Error] AI Invalid Move!");
        System.out.println("Player " + player.getID() + " has moved to " + move.getX() + " " + move.getY() );
        board = BoardUtils.getNewBoardAfterMove(board, move,player.getID());
        turn = (turn == 1 ? 2 : 1);
        gameWindow.getGameInterface().repaint(board);


    }

    public void resetBoard(){
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        board[3][3] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        board[4][4] = 1;
        turn = 1;
        gameWindow.getGameInterface().resetHighlights();
        gameWindow.getGameInterface().resetBoard();
        gameWindow.getGameInterface().repaint(board);
        updateBoardInfo();
    }

    public void manageTurn(){
        if(forceQuit){
            forceQuit = false;
            System.out.println("Game finished!");
            return;
        }
        if(BoardUtils.hasAnyMoves(board,1) || BoardUtils.hasAnyMoves(board,2)) {
            updateBoardInfo();
            if (turn == 1) {
                if(BoardUtils.hasAnyMoves(board,1)) {
                    if(player1.type().equals("Human Player")){
                        awaitForClick = true;
                    }
                    else{
                        player1HandlerTimer.start();
                    }
                }else{
                    System.out.println("Player 1 has no moves !");
                    turn = 2;
                    manageTurn();
                }
            } else {
                if(BoardUtils.hasAnyMoves(board,2)) {
                    if(player2.type().equals("Human Player")){
                        awaitForClick = true;
                    }
                    else{
                        player2HandlerTimer.start();
                    }
                }else{
                    System.out.println("Player 2 has no moves !");
                    turn = 1;
                    manageTurn();
                }
            }
        }else{
            //game finished
            if(forceQuit){
                forceQuit = false;
                return;
            }
            System.out.println("Game Finished!");
            int winner = BoardUtils.getWinner(board);
            gameWindow.weHaveAWinner(winner == 1 ? player1 : player2, winner == 1 ? player2: player1);
        }
    }

    public int getPlayerScore(int ID){
        if(ID == 1) return getPlayer1Score();
        else if(ID == 2) return getPlayer2Score();

        else return -1;
    }


    public void endGame() {
        this.forceQuit = true;

        resetBoard();
        player1 = null;
        player2 = null;
        this.gameWindow.getOptionsInterface().reset();
    }


    public void setBoardValue(int row, int column, int value){
        board[row][column] = value;
        gameWindow.getGameInterface().repaint(board);
    }

    public int getBoardValue(int row, int column){
        return board[row][column];
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public boolean isAwaitForClick() {
        return awaitForClick;
    }

    public void setAwaitForClick(boolean awaitForClick) {
        this.awaitForClick = awaitForClick;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


}
