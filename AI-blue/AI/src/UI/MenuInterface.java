package UI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuInterface extends JPanel{
    private GameWindow window;
    private List<JButton> buttons;

    public MenuInterface(GameWindow window){
        this.window = window;
        buttons = new ArrayList<>();
        init();
    }

    public void init(){
        JButton playButton = new JButton("Start");
        playButton.setSize(200,100);
        playButton.addMouseListener(new MouseAdapter() {
            boolean pressed = false;


            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(pressed) window.switchInterface(window.OPTIONS_INTERFACE_NAME);
                pressed = false;
            }
        });
        this.buttons.add(playButton);
        add(playButton);


        JButton quitButton = new JButton("Quit");
        quitButton.setSize(200,100);
        quitButton.addMouseListener(new MouseAdapter() {
            boolean pressed = false;

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(pressed) window.quit();
            }
        });
        this.buttons.add(quitButton);
        add(quitButton);

    }

    public GameWindow getWindow() {
        return window;
    }

    public void setWindow(GameWindow window) {
        this.window = window;
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<JButton> buttons) {
        this.buttons = buttons;
    }
}
