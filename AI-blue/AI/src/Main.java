import UI.GameWindow;
import game.Game;

public class Main {

    public static void main(String[] args) {
	    Game game = new Game();
	    GameWindow gameWindow = new GameWindow(game);
	    game.setGameWindow(gameWindow);
	    gameWindow.setVisible(true);

//	    game.start();
    }
}
