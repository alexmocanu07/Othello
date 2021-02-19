package utils;
import java.util.ArrayList;

public class BoardUtils {
    public static boolean isGameFinished(int[][] board) {
        return !(hasAnyMoves(board, 1) || hasAnyMoves(board, 2));
    }


    public static int[][] getStartBoard() {
        int[][] b = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b[i][j] = 0;
            }
        }
        b[3][3] = 2;
        b[3][4] = 1;
        b[4][3] = 1;
        b[4][4] = 2;
        return b;
    }

    public static Point getMove(int[][] before, int[][] after) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (before[i][j] == 0 && after[i][j] != 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public static int getWinner(int[][] board) {
        if (!isGameFinished(board))

            return -1;
        else {

            int player1Score = getPlayerScore(board, 1);
            int player2Score = getPlayerScore(board, 2);

            if (player1Score == player2Score) {
                return 0;
            } else if (player1Score > player2Score) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    public static int getTotalScore(int[][] board) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0) score++;
            }
        }
        return score;
    }

    public static int getPlayerScore(int[][] board, int player) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) score++;
            }
        }
        return score;
    }


    public static boolean hasAnyMoves(int[][] board, int player) {
        return getAllPossibleMoves(board, player).size() > 0;
    }

    public static ArrayList<Point> getAllPossibleMoves(int[][] board, int player) {
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPlay(board, player, i, j)) {
                    result.add(new Point(i, j));
                }
            }
        }
        return result;
    }

    public static ArrayList<Point> getReversedPieces(int[][] board, int player, int row, int column) {
        /*
            Caut pe toate directiile un sir continuu de piese ale inamicului, care incepe la pozitia curenta +-1.
            Daca la capatul celalalt exista o piesa de-a mea, adaug coordonatele punctelor din sirul respectiv la o lista de pct reverisbile
        */

        ArrayList<Point> allReversePoints = new ArrayList<>();

        int i, j;
        int otherPlayer = ((player == 1) ? 2 : 1);

        //move up
        ArrayList<Point> upwardsTiles = new ArrayList<>();
        i = row - 1;
        j = column;
        while (i > 0 && board[i][j] == otherPlayer) {
            upwardsTiles.add(new Point(i, j));
            i--;
        }
        if (i >= 0 && board[i][j] == player && upwardsTiles.size() > 0) {
            allReversePoints.addAll(upwardsTiles);
        }


        //move down
        ArrayList<Point> downwardsTiles = new ArrayList<>();
        i = row + 1;
        j = column;
        while (i < 7 && board[i][j] == otherPlayer) {
            downwardsTiles.add(new Point(i, j));
            i++;
        }
        if (i <= 7 && board[i][j] == player && downwardsTiles.size() > 0) {
            allReversePoints.addAll(downwardsTiles);
        }

        //move left
        ArrayList<Point> leftTiles = new ArrayList<>();
        i = row;
        j = column - 1;
        while (j > 0 && board[i][j] == otherPlayer) {
            leftTiles.add(new Point(i, j));
            j--;
        }
        if (j >= 0 && board[i][j] == player && leftTiles.size() > 0) {
            allReversePoints.addAll(leftTiles);
        }

        //move right
        ArrayList<Point> rightTiles = new ArrayList<>();
        i = row;
        j = column + 1;
        while (j < 7 && board[i][j] == otherPlayer) {
            rightTiles.add(new Point(i, j));
            j++;
        }
        if (j <= 7 && board[i][j] == player && rightTiles.size() > 0) {
            allReversePoints.addAll(rightTiles);
        }

        //move up left
        ArrayList<Point> upLeftTiles = new ArrayList<>();
        i = row - 1;
        j = column - 1;
        while (i > 0 && j > 0 && board[i][j] == otherPlayer) {
            upLeftTiles.add(new Point(i, j));
            i--;
            j--;
        }
        if (i >= 0 && j >= 0 && board[i][j] == player && upLeftTiles.size() > 0) {
            allReversePoints.addAll(upLeftTiles);
        }

        //move up right
        ArrayList<Point> upRightTiles = new ArrayList<>();
        i = row - 1;
        j = column + 1;
        while (i > 0 && j < 7 && board[i][j] == otherPlayer) {
            upRightTiles.add(new Point(i, j));
            i--;
            j++;
        }
        if (i >= 0 && j <= 7 && board[i][j] == player && upRightTiles.size() > 0) {
            allReversePoints.addAll(upRightTiles);
        }

        //move down left
        ArrayList<Point> downLeftTiles = new ArrayList<>();
        i = row + 1;
        j = column - 1;
        while (i < 7 && j > 0 && board[i][j] == otherPlayer) {
            downLeftTiles.add(new Point(i, j));
            i++;
            j--;
        }
        if (i <= 7 && j >= 0 && board[i][j] == player && downLeftTiles.size() > 0) {
            allReversePoints.addAll(downLeftTiles);
        }

        //move down right
        ArrayList<Point> downRightTiles = new ArrayList<>();
        i = row + 1;
        j = column + 1;
        while (i < 7 && j < 7 && board[i][j] == otherPlayer) {
            downRightTiles.add(new Point(i, j));
            i++;
            j++;
        }
        if (i <= 7 && j <= 7 && board[i][j] == player && downRightTiles.size() > 0) {
            allReversePoints.addAll(downRightTiles);
        }

        return allReversePoints;
    }

    public static boolean canPlay(int[][] board, int player, int row, int column) {

        if (board[row][column] != 0) return false;

        int i, j, count;
        int otherPlayer = ((player == 1) ? 2 : 1);

        //move up
        i = row - 1;
        j = column;
        count = 0;
        while (i > 0 && board[i][j] == otherPlayer) {
            i--;
            count++;
        }
        if (i >= 0 && board[i][j] == player && count > 0) return true;


        //move down
        i = row + 1;
        j = column;
        count = 0;
        while (i < 7 && board[i][j] == otherPlayer) {
            i++;
            count++;
        }
        if (i <= 7 && board[i][j] == player && count > 0) return true;

        //move left
        i = row;
        j = column - 1;
        count = 0;
        while (j > 0 && board[i][j] == otherPlayer) {
            j--;
            count++;
        }
        if (j >= 0 && board[i][j] == player && count > 0) return true;

        //move right
        i = row;
        j = column + 1;
        count = 0;
        while (j < 7 && board[i][j] == otherPlayer) {
            j++;
            count++;
        }
        if (j <= 7 && board[i][j] == player && count > 0) return true;

        //move up left
        i = row - 1;
        j = column - 1;
        count = 0;
        while (i > 0 && j > 0 && board[i][j] == otherPlayer) {
            i--;
            j--;
            count++;
        }
        if (i >= 0 && j >= 0 && board[i][j] == player && count > 0) return true;

        //move up right
        i = row - 1;
        j = column + 1;
        count = 0;
        while (i > 0 && j < 7 && board[i][j] == otherPlayer) {
            i--;
            j++;
            count++;
        }
        if (i >= 0 && j <= 7 && board[i][j] == player && count > 0) return true;

        //move down left
        i = row + 1;
        j = column - 1;
        count = 0;
        while (i < 7 && j > 0 && board[i][j] == otherPlayer) {
            i++;
            j--;
            count++;
        }
        if (i <= 7 && j >= 0 && board[i][j] == player && count > 0) return true;

        //move down right
        i = row + 1;
        j = column + 1;
        count = 0;
        while (i < 7 && j < 7 && board[i][j] == otherPlayer) {
            i++;
            j++;
            count++;
        }
        if (i <= 7 && j <= 7 && board[i][j] == player && count > 0) return true;

        // nu exista un sir de piese ale oponentului care se termina in piesa de-a mea
        return false;
    }

    public static int[][] getNewBoardAfterMove(int[][] board, Point move, int player) {

        int[][] newboard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newboard[i][j] = board[i][j];
            }
        }

        newboard[move.getX()][move.getY()] = player;
        ArrayList<Point> reversed = BoardUtils.getReversedPieces(newboard, player, move.getX(), move.getY()); // toate punctele pe care le intorc cu miscarea curenta
        for (Point current : reversed) {
            newboard[current.getX()][current.getY()] = player;
        }

        return newboard;
    }

    public static ArrayList<Point> getIrreversibleTiles(int[][] board, int player, int row, int column) {

        ArrayList<Point> irreversibleTiles = new ArrayList<>();

        int i, j;
        int otherPlayer = ((player == 1) ? 2 : 1);

        //move up
        ArrayList<Point> upwardsTiles = new ArrayList<>();
        i = row - 1;
        j = column;
        while (i > 0 && board[i][j] == player) {
            upwardsTiles.add(new Point(i, j));
            i--;
        }
        for (Point current : upwardsTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move down
        ArrayList<Point> downwardsTiles = new ArrayList<>();
        i = row + 1;
        j = column;
        while (i < 7 && board[i][j] == otherPlayer) {
            downwardsTiles.add(new Point(i, j));
            i++;
        }
        for (Point current : downwardsTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move left
        ArrayList<Point> leftTiles = new ArrayList<>();
        i = row;
        j = column - 1;
        while (j > 0 && board[i][j] == otherPlayer) {
            leftTiles.add(new Point(i, j));
            j--;
        }
        for (Point current : leftTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move right
        ArrayList<Point> rightTiles = new ArrayList<>();
        i = row;
        j = column + 1;
        while (j < 7 && board[i][j] == otherPlayer) {
            rightTiles.add(new Point(i, j));
            j++;
        }
        for (Point current : rightTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move up left
        ArrayList<Point> upLeftTiles = new ArrayList<>();
        i = row - 1;
        j = column - 1;
        while (i > 0 && j > 0 && board[i][j] == otherPlayer) {
            upLeftTiles.add(new Point(i, j));
            i--;
            j--;
        }
        for (Point current : upLeftTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move up right
        ArrayList<Point> upRightTiles = new ArrayList<>();
        i = row - 1;
        j = column + 1;
        while (i > 0 && j < 7 && board[i][j] == otherPlayer) {
            upRightTiles.add(new Point(i, j));
            i--;
            j++;
        }
        for (Point current : upRightTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move down left
        ArrayList<Point> downLeftTiles = new ArrayList<>();
        i = row + 1;
        j = column - 1;
        while (i < 7 && j > 0 && board[i][j] == otherPlayer) {
            downLeftTiles.add(new Point(i, j));
            i++;
            j--;
        }
        for (Point current : downLeftTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        //move down right
        ArrayList<Point> downRightTiles = new ArrayList<>();
        i = row + 1;
        j = column + 1;
        while (i < 7 && j < 7 && board[i][j] == otherPlayer) {
            downRightTiles.add(new Point(i, j));
            i++;
            j++;
        }
        for (Point current : downRightTiles) {
            if (!irreversibleTiles.contains(current)) irreversibleTiles.add(current);
        }

        return irreversibleTiles;
    }
}

