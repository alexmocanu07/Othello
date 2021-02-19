package UI;

import game.Game;
import players.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    public final String MENU_INTERFACE_NAME = "menu";
    public final String OPTIONS_INTERFACE_NAME = "options";
    public final String GAME_INTERFACE_NAME = "game";
    MenuInterface menuInterface;
    OptionsInterface optionsInterface;
    GameInterface gameInterface;

    Game game;
    int currentInterfaceID;
    private List<JComponent> interfaces;
    private JPanel GUI;
    private CardLayout cardLayout;

    public GameWindow(Game game){
        super("Othello");
        this.game = game;
        init();
    }

    private void init(){
        this.cardLayout = new CardLayout();
        this.GUI = new JPanel(cardLayout);
        add(GUI);
        setSize(765,605);
        setDefaultLookAndFeelDecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.interfaces = new ArrayList<>();

        menuInterface = new MenuInterface(this);
//        menuInterface.setVisible(true);
        this.GUI.add(menuInterface, "menu");
        this.interfaces.add(menuInterface);
        this.currentInterfaceID = 0;

        this.optionsInterface = new OptionsInterface(this);

//        this.algorithmInterface.getOptionsPanel().setVisible(false);
        this.GUI.add(this.optionsInterface.getOptionsPanel(), "options");
//        this.interfaces.add(this.algorithmInterface.getOptionsPanel());

        this.gameInterface = new GameInterface(this);
        this.GUI.add(this.gameInterface.getGamePanel(), "game");


    }

    public void weHaveAWinner(Player winner, Player loser){
        JOptionPane.showMessageDialog(this ,"Player " + winner.getID() +" has won!\n" +
                winner.type()  + " scored " + game.getPlayerScore(winner.getID()) + "\n" +
                loser.type() + " scored " + game.getPlayerScore(loser.getID()));
    }

    public void highlight(int row, int column){
        this.gameInterface.highlight(row, column);
    }

    public void switchInterface(String newInterface){
        this.cardLayout.show(GUI, newInterface);
    }

    public void quit(){
        System.exit(1);
    }

    public int getCurrentInterfaceID() {
        return currentInterfaceID;
    }

    public void setCurrentInterfaceID(int currentInterfaceID) {
        this.currentInterfaceID = currentInterfaceID;
    }

    public List<JComponent> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<JComponent> interfaces) {
        this.interfaces = interfaces;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public MenuInterface getMenuInterface() {
        return menuInterface;
    }

    public void setMenuInterface(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    public OptionsInterface getOptionsInterface() {
        return optionsInterface;
    }

    public void setOptionsInterface(OptionsInterface optionsInterface) {
        this.optionsInterface = optionsInterface;
    }

    public GameInterface getGameInterface() {
        return gameInterface;
    }

    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }
}
