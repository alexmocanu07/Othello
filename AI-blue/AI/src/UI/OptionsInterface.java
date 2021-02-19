package UI;

import players.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionsInterface extends JComponent {

    private JTextField algorithmTextFIeld;
    private JPanel optionsPanel;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JPanel middlePanel;
    private JRadioButton dynamicAIPlayerRadioButton1;
    private JRadioButton staticAIPlayerRadioButton1;
    private JRadioButton gamePhaseAIPlayerRadioButton1;
    private JRadioButton greedyAIPlayerRadioButton1;
    private JRadioButton randomAIPlayerRadioButton1;
    private JRadioButton douchebagAIPlayerRadioButton1;
    private JRadioButton humanPlayerRadioButton1;
    private JRadioButton randomAIPlayerRadioButton2;
    private JRadioButton greedyAIPlayerRadioButton2;
    private JRadioButton gamePhaseAIPlayerRadioButton2;
    private JRadioButton staticAIPlayerRadioButton2;
    private JRadioButton dynamicAIPlayerRadioButton2;
    private JRadioButton douchebagAIPlayerRadioButton2;
    private JRadioButton humanPlayerRadioButton2;
    private JButton readyButton;
    private JButton backButton;
    private JLabel player2Label;
    private JLabel player1Label;

    private GameWindow window;

    public OptionsInterface(GameWindow window) {
        this.window = window;
        this.addListeners();

    }


    private void addListeners() {
        backButton.addMouseListener(new MouseAdapter() {
            boolean pressed = false;


            @Override
            public void mousePressed(MouseEvent e) {
                if (window.getGame().isAwaitForClick()) pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (pressed) {
                    window.switchInterface(window.MENU_INTERFACE_NAME);
                    pressed = false;
                }
            }
        });

        readyButton.addMouseListener(new MouseAdapter() {
            boolean pressed = false;

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Player leftSelectedPlayer = getPlayer1PressedRadio();
                Player rightSelectedPlayer = getPlayer2PressedRadio();

                if (leftSelectedPlayer == null || rightSelectedPlayer == null) {
                    JOptionPane.showMessageDialog(window, "Both player types must be selected!");
                } else {
                    window.getGame().setPlayer1(leftSelectedPlayer);
                    window.getGame().setPlayer2(rightSelectedPlayer);
                    window.switchInterface(window.GAME_INTERFACE_NAME);
                    window.getGame().start();
                }
            }
        });
    }

    private Player getPlayer1PressedRadio() {
        if (humanPlayerRadioButton1.isSelected()) return new HumanPlayer(1);
        if (douchebagAIPlayerRadioButton1.isSelected()) return new KillerCornerBlockerPlayer(1);
        if (dynamicAIPlayerRadioButton1.isSelected()) return new WeightedDynamicPlayer(1);
        if (gamePhaseAIPlayerRadioButton1.isSelected()) return new GamePhasePlayer(1);
        if (staticAIPlayerRadioButton1.isSelected()) return new StaticPlayer(1);
        if (greedyAIPlayerRadioButton1.isSelected()) return new GreedyPlayer(1);
        if (randomAIPlayerRadioButton1.isSelected()) return new RandomPlayer(1);

        return null;
    }

    private Player getPlayer2PressedRadio() {
        if (humanPlayerRadioButton2.isSelected()) return new HumanPlayer(2);
        if (douchebagAIPlayerRadioButton2.isSelected()) return new KillerCornerBlockerPlayer(2);
        if (dynamicAIPlayerRadioButton2.isSelected()) return new WeightedDynamicPlayer(2);
        if (gamePhaseAIPlayerRadioButton2.isSelected()) return new GamePhasePlayer(2);
        if (staticAIPlayerRadioButton2.isSelected()) return new StaticPlayer(2);
        if (greedyAIPlayerRadioButton2.isSelected()) return new GreedyPlayer(2);
        if (randomAIPlayerRadioButton2.isSelected()) return new RandomPlayer(2);

        return null;
    }

    public void reset() {
        if (humanPlayerRadioButton1.isSelected()) humanPlayerRadioButton1.doClick();
        if (douchebagAIPlayerRadioButton1.isSelected()) douchebagAIPlayerRadioButton1.doClick();
        if (dynamicAIPlayerRadioButton1.isSelected()) dynamicAIPlayerRadioButton1.doClick();
        if (gamePhaseAIPlayerRadioButton1.isSelected()) gamePhaseAIPlayerRadioButton1.doClick();
        if (staticAIPlayerRadioButton1.isSelected()) staticAIPlayerRadioButton1.doClick();
        if (greedyAIPlayerRadioButton1.isSelected()) greedyAIPlayerRadioButton1.doClick();
        if (randomAIPlayerRadioButton1.isSelected()) randomAIPlayerRadioButton1.doClick();

        if (humanPlayerRadioButton2.isSelected()) humanPlayerRadioButton2.doClick();
        if (douchebagAIPlayerRadioButton2.isSelected()) douchebagAIPlayerRadioButton2.doClick();
        if (dynamicAIPlayerRadioButton2.isSelected()) dynamicAIPlayerRadioButton2.doClick();
        if (gamePhaseAIPlayerRadioButton2.isSelected()) gamePhaseAIPlayerRadioButton2.doClick();
        if (staticAIPlayerRadioButton2.isSelected()) staticAIPlayerRadioButton2.doClick();
        if (greedyAIPlayerRadioButton2.isSelected()) greedyAIPlayerRadioButton2.doClick();
        if (randomAIPlayerRadioButton2.isSelected()) randomAIPlayerRadioButton2.doClick();
    }


    public JTextField getAlgorithmTextFIeld() {
        return algorithmTextFIeld;
    }

    public void setAlgorithmTextFIeld(JTextField algorithmTextFIeld) {
        this.algorithmTextFIeld = algorithmTextFIeld;
    }

    public JPanel getOptionsPanel() {
        return optionsPanel;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        player1Panel = new JPanel();
        player1Panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        optionsPanel.add(player1Panel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dynamicAIPlayerRadioButton1 = new JRadioButton();
        dynamicAIPlayerRadioButton1.setText("Dynamic AI Player");
        player1Panel.add(dynamicAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        staticAIPlayerRadioButton1 = new JRadioButton();
        staticAIPlayerRadioButton1.setText("Static AI Player");
        player1Panel.add(staticAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gamePhaseAIPlayerRadioButton1 = new JRadioButton();
        gamePhaseAIPlayerRadioButton1.setText("GamePhase AI Player");
        player1Panel.add(gamePhaseAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        greedyAIPlayerRadioButton1 = new JRadioButton();
        greedyAIPlayerRadioButton1.setText("Greedy AI Player");
        player1Panel.add(greedyAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        randomAIPlayerRadioButton1 = new JRadioButton();
        randomAIPlayerRadioButton1.setText("Random AI Player");
        player1Panel.add(randomAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        douchebagAIPlayerRadioButton1 = new JRadioButton();
        douchebagAIPlayerRadioButton1.setText("Douchebag AI Player");
        player1Panel.add(douchebagAIPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        humanPlayerRadioButton1 = new JRadioButton();
        humanPlayerRadioButton1.setText("Human Player");
        player1Panel.add(humanPlayerRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player1Label = new JLabel();
        player1Label.setHorizontalAlignment(10);
        player1Label.setHorizontalTextPosition(11);
        player1Label.setText("Player1");
        player1Panel.add(player1Label, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        player1Panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        player1Panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        player1Panel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        player1Panel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        player2Panel = new JPanel();
        player2Panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        optionsPanel.add(player2Panel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        randomAIPlayerRadioButton2 = new JRadioButton();
        randomAIPlayerRadioButton2.setText("Random AI Player");
        player2Panel.add(randomAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        greedyAIPlayerRadioButton2 = new JRadioButton();
        greedyAIPlayerRadioButton2.setText("Greedy AI Player");
        player2Panel.add(greedyAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gamePhaseAIPlayerRadioButton2 = new JRadioButton();
        gamePhaseAIPlayerRadioButton2.setText("GamePhase AI Player");
        player2Panel.add(gamePhaseAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        staticAIPlayerRadioButton2 = new JRadioButton();
        staticAIPlayerRadioButton2.setText("Static AI Player");
        player2Panel.add(staticAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dynamicAIPlayerRadioButton2 = new JRadioButton();
        dynamicAIPlayerRadioButton2.setText("Dynamic AI Player");
        player2Panel.add(dynamicAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        douchebagAIPlayerRadioButton2 = new JRadioButton();
        douchebagAIPlayerRadioButton2.setText("Douchebag AI Player");
        player2Panel.add(douchebagAIPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        humanPlayerRadioButton2 = new JRadioButton();
        humanPlayerRadioButton2.setText("Human Player");
        player2Panel.add(humanPlayerRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        player2Panel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        player2Label = new JLabel();
        player2Label.setText("Player2");
        player2Panel.add(player2Label, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        player2Panel.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        player2Panel.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        player2Panel.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        middlePanel = new JPanel();
        middlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        optionsPanel.add(middlePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        middlePanel.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        readyButton = new JButton();
        readyButton.setText("Ready");
        middlePanel.add(readyButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        middlePanel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return optionsPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
