package players;
import utils.Point;

public abstract class Player {
    protected int ID;
    public Player(int ID){this.ID = ID;}
    public abstract String type();
    public abstract Point getMove(int[][] board);

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
