package players;

import utils.Point;

public class HumanPlayer extends Player {
    public HumanPlayer(int ID){
        super(ID);
    }

    @Override
    public String type() {
        return "Human Player";
    }

    @Override
    public Point getMove(int[][] board) {
        return null;
    }
}
