package UI;

import players.Player;
import utils.BoardUtils;
import utils.CoordButton;
import utils.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameInterface extends JComponent {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JButton b11;
    private JButton b12;
    private JButton b13;
    private JButton b14;
    private JButton b15;
    private JButton b16;
    private JButton b24;
    private JButton b32;
    private JButton b40;
    private JButton b48;
    private JButton b56;
    private JButton b64;
    private JButton b23;
    private JButton b22;
    private JButton b21;
    private JButton b20;
    private JButton b19;
    private JButton b18;
    private JButton b17;
    private JButton b25;
    private JButton b26;
    private JButton b27;
    private JButton b28;
    private JButton b29;
    private JButton b30;
    private JButton b31;
    private JButton b33;
    private JButton b34;
    private JButton b35;
    private JButton b36;
    private JButton b37;
    private JButton b38;
    private JButton b39;
    private JButton b41;
    private JButton b42;
    private JButton b43;
    private JButton b44;
    private JButton b45;
    private JButton b46;
    private JButton b47;
    private JButton b49;
    private JButton b50;
    private JButton b51;
    private JButton b52;
    private JButton b53;
    private JButton b54;
    private JButton b55;
    private JButton b57;
    private JButton b58;
    private JButton b59;
    private JButton b60;
    private JButton b61;
    private JButton b62;
    private JButton b63;
    private JPanel boardPanel;
    private JPanel gamePanel;
    private JPanel ScorePanel;
    //    private JTextArea scoreTextArea;
    private JButton backBtn;
    private JPanel player1ScorePanel;
    private JPanel player2ScorePanel;
    private JLabel player1Score;
    private JLabel player2Score;
    private JLabel player2Type;
    private JLabel player1Label;
    private JLabel player1Type;
    private JLabel player2Label;

    private GameWindow window;
    private List<CoordButton> buttons;
    private List<CoordButton> highlightedButtons = new ArrayList<>();

    public GameInterface(GameWindow window) {
        this.window = window;
        this.addButtonsToList();
        this.addListeners();
        this.setButtonsIcons();
        this.setPlayerText(this.window.getGame().getPlayer1(), this.window.getGame().getPlayer2());
    }

    public void setPlayerText(Player player1, Player player2) {
        if (player1 == null || player2 == null) return;
        player1Type.setText("Type: " + player1.type());
        player2Type.setText("Type: " + player2.type());
        this.setPlayerTextScores(this.window.getGame().getPlayer1Score(), this.window.getGame().getPlayer2Score());
    }

    public void setPlayerTextScores(int player1Score, int player2Score) {
        this.player1Score.setText("Score: " + player1Score);
        this.player2Score.setText("Score: " + player2Score);
    }

    public JButton getButtonByCoord(int row, int column) {
        Point coord = new Point(row, column);
        for (CoordButton current : buttons) {
            if (coord.equals(current.getCoord())) return current.getButton();
        }
        return null;
    }

    public void highlight(int row, int column) {


        JButton highlightButton = getButtonByCoord(row, column);
        if (highlightButton != null) {
            try {
                Image img = ImageIO.read(getClass().getResource("../icons/hovered.png"));
                highlightButton.setIcon(new ImageIcon(img));
                highlightedButtons.add(new CoordButton(highlightButton, new Point(row, column)));
            } catch (Exception exc) {
                System.out.println("Image error highlight");
            }
        }
    }

    public void resetHighlights() {
        if (highlightedButtons == null) return;
        for (CoordButton current : highlightedButtons) {
            try {
                Image img = ImageIO.read(getClass().getResource("../icons/plain.png"));
                current.getButton().setIcon(new ImageIcon(img));

            } catch (Exception exc) {
                System.out.println("Image error reset highlight");
            }
        }
        highlightedButtons = new ArrayList<>();

    }

    public void repaint(int[][] board) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0) {
                    JButton current = getButtonByCoord(i, j);
                    try {
                        Image img;
                        if (board[i][j] == 1)
                            img = ImageIO.read(getClass().getResource("../icons/black.png"));
                        else
                            img = ImageIO.read(getClass().getResource("../icons/white.png"));
                        current.setIcon(new ImageIcon(img));

                    } catch (Exception exc) {
                        System.out.println("Image error repaint");
                    }
                }
            }
    }

    public void resetBoard() {
        for (CoordButton current : buttons) {
            try {
                Image img;
                img = ImageIO.read(getClass().getResource("../icons/plain.png"));
                current.getButton().setIcon(new ImageIcon(img));

            } catch (Exception exc) {
                System.out.println("Image error reset");
            }
        }
    }

    private void addListeners() {
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.getGame().endGame();
//                window.getGame().resetBoard();
                window.switchInterface(window.MENU_INTERFACE_NAME);
            }
        });

        for (CoordButton current : buttons) {
            current.getButton().addMouseListener(new MouseAdapter() {
                boolean pressed = false;


                @Override
                public void mousePressed(MouseEvent e) {
                    if (window.getGame().isAwaitForClick()) {
                        pressed = true;

                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (pressed && BoardUtils.canPlay(window.getGame().getBoard(), window.getGame().getTurn(), current.getCoord().getX(), current.getCoord().getY())) {
//                        try {
//                            Image img = ImageIO.read(getClass().getResource("../icons/black.gif"));
//                            current.getButton().setIcon(new ImageIcon(img));
//                            current.setColor("black");
//                        } catch (Exception exc) {
//                            System.out.println("Image error release");
//                        }
                        window.getGame().setBoard(BoardUtils.getNewBoardAfterMove(window.getGame().getBoard(), current.getCoord(), window.getGame().getTurn()));
                        System.out.println("Player " + window.getGame().getTurn() + " has moved to " + current.getCoord().getX() + " " + current.getCoord().getY());
                        window.getGame().setTurn(window.getGame().getTurn() == 1 ? 2 : 1);
                        window.getGame().setAwaitForClick(false);
                        resetHighlights();
                        pressed = false;
                        repaint(window.getGame().getBoard());
                        window.getGame().manageTurn();

                    }
                }
            });
        }
    }

    private void setButtonsIcons() {
        for (CoordButton current : buttons) {
            current.getButton().setFocusPainted(true);
            current.getButton().setContentAreaFilled(false);
            current.getButton().setMargin(new Insets(0, 0, 0, 0));
            current.getButton().setBorder(BorderFactory.createEmptyBorder());
            try {
                Image img = ImageIO.read(getClass().getResource("../icons/plain.png"));
                current.getButton().setIcon(new ImageIcon(img));
                current.setColor("none");
            } catch (Exception exc) {
                System.out.println("Image error set UI.icons");
            }
        }
    }


    private void addButtonsToList() {
        this.buttons = new ArrayList<>();
        buttons.add(new CoordButton(b1, new Point(0, 0)));
        buttons.add(new CoordButton(b2, new Point(0, 1)));
        buttons.add(new CoordButton(b3, new Point(0, 2)));
        buttons.add(new CoordButton(b4, new Point(0, 3)));
        buttons.add(new CoordButton(b5, new Point(0, 4)));
        buttons.add(new CoordButton(b6, new Point(0, 5)));
        buttons.add(new CoordButton(b7, new Point(0, 6)));
        buttons.add(new CoordButton(b8, new Point(0, 7)));
        buttons.add(new CoordButton(b9, new Point(1, 0)));
        buttons.add(new CoordButton(b10, new Point(1, 1)));
        buttons.add(new CoordButton(b11, new Point(1, 2)));
        buttons.add(new CoordButton(b12, new Point(1, 3)));
        buttons.add(new CoordButton(b13, new Point(1, 4)));
        buttons.add(new CoordButton(b14, new Point(1, 5)));
        buttons.add(new CoordButton(b15, new Point(1, 6)));
        buttons.add(new CoordButton(b16, new Point(1, 7)));
        buttons.add(new CoordButton(b17, new Point(2, 0)));
        buttons.add(new CoordButton(b18, new Point(2, 1)));
        buttons.add(new CoordButton(b19, new Point(2, 2)));
        buttons.add(new CoordButton(b20, new Point(2, 3)));
        buttons.add(new CoordButton(b21, new Point(2, 4)));
        buttons.add(new CoordButton(b22, new Point(2, 5)));
        buttons.add(new CoordButton(b23, new Point(2, 6)));
        buttons.add(new CoordButton(b24, new Point(2, 7)));
        buttons.add(new CoordButton(b25, new Point(3, 0)));
        buttons.add(new CoordButton(b26, new Point(3, 1)));
        buttons.add(new CoordButton(b27, new Point(3, 2)));
        buttons.add(new CoordButton(b28, new Point(3, 3)));
        buttons.add(new CoordButton(b29, new Point(3, 4)));
        buttons.add(new CoordButton(b30, new Point(3, 5)));
        buttons.add(new CoordButton(b31, new Point(3, 6)));
        buttons.add(new CoordButton(b32, new Point(3, 7)));
        buttons.add(new CoordButton(b33, new Point(4, 0)));
        buttons.add(new CoordButton(b34, new Point(4, 1)));
        buttons.add(new CoordButton(b35, new Point(4, 2)));
        buttons.add(new CoordButton(b36, new Point(4, 3)));
        buttons.add(new CoordButton(b37, new Point(4, 4)));
        buttons.add(new CoordButton(b38, new Point(4, 5)));
        buttons.add(new CoordButton(b39, new Point(4, 6)));
        buttons.add(new CoordButton(b40, new Point(4, 7)));
        buttons.add(new CoordButton(b41, new Point(5, 0)));
        buttons.add(new CoordButton(b42, new Point(5, 1)));
        buttons.add(new CoordButton(b43, new Point(5, 2)));
        buttons.add(new CoordButton(b44, new Point(5, 3)));
        buttons.add(new CoordButton(b45, new Point(5, 4)));
        buttons.add(new CoordButton(b46, new Point(5, 5)));
        buttons.add(new CoordButton(b47, new Point(5, 6)));
        buttons.add(new CoordButton(b48, new Point(5, 7)));
        buttons.add(new CoordButton(b49, new Point(6, 0)));
        buttons.add(new CoordButton(b50, new Point(6, 1)));
        buttons.add(new CoordButton(b51, new Point(6, 2)));
        buttons.add(new CoordButton(b52, new Point(6, 3)));
        buttons.add(new CoordButton(b53, new Point(6, 4)));
        buttons.add(new CoordButton(b54, new Point(6, 5)));
        buttons.add(new CoordButton(b55, new Point(6, 6)));
        buttons.add(new CoordButton(b56, new Point(6, 7)));
        buttons.add(new CoordButton(b57, new Point(7, 0)));
        buttons.add(new CoordButton(b58, new Point(7, 1)));
        buttons.add(new CoordButton(b59, new Point(7, 2)));
        buttons.add(new CoordButton(b60, new Point(7, 3)));
        buttons.add(new CoordButton(b61, new Point(7, 4)));
        buttons.add(new CoordButton(b62, new Point(7, 5)));
        buttons.add(new CoordButton(b63, new Point(7, 6)));
        buttons.add(new CoordButton(b64, new Point(7, 7)));


    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(JPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public JPanel getScorePanel() {
        return ScorePanel;
    }

    public void setScorePanel(JPanel scorePanel) {
        ScorePanel = scorePanel;
    }


    public GameWindow getWindow() {
        return window;
    }

    public void setWindow(GameWindow window) {
        this.window = window;
    }

    public List<CoordButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<CoordButton> buttons) {
        this.buttons = buttons;
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
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        gamePanel.setBackground(new Color(-16731393));
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        boardPanel.setMaximumSize(new Dimension(550, 550));
        boardPanel.setMinimumSize(new Dimension(400, 400));
        boardPanel.setPreferredSize(new Dimension(520, 520));
        boardPanel.setRequestFocusEnabled(false);
        boardPanel.setVerifyInputWhenFocusTarget(true);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gamePanel.add(boardPanel, gbc);
        boardPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        b1 = new JButton();
        b1.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b1, gbc);
        b2 = new JButton();
        b2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b2, gbc);
        b4 = new JButton();
        b4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b4, gbc);
        b5 = new JButton();
        b5.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b5, gbc);
        b6 = new JButton();
        b6.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b6, gbc);
        b7 = new JButton();
        b7.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b7, gbc);
        b8 = new JButton();
        b8.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b8, gbc);
        b9 = new JButton();
        b9.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b9, gbc);
        b10 = new JButton();
        b10.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b10, gbc);
        b11 = new JButton();
        b11.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b11, gbc);
        b12 = new JButton();
        b12.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b12, gbc);
        b13 = new JButton();
        b13.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b13, gbc);
        b14 = new JButton();
        b14.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b14, gbc);
        b15 = new JButton();
        b15.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b15, gbc);
        b16 = new JButton();
        b16.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b16, gbc);
        b24 = new JButton();
        b24.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b24, gbc);
        b32 = new JButton();
        b32.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b32, gbc);
        b40 = new JButton();
        b40.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b40, gbc);
        b48 = new JButton();
        b48.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b48, gbc);
        b56 = new JButton();
        b56.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b56, gbc);
        b64 = new JButton();
        b64.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b64, gbc);
        b23 = new JButton();
        b23.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b23, gbc);
        b22 = new JButton();
        b22.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b22, gbc);
        b21 = new JButton();
        b21.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b21, gbc);
        b20 = new JButton();
        b20.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b20, gbc);
        b19 = new JButton();
        b19.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b19, gbc);
        b18 = new JButton();
        b18.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b18, gbc);
        b17 = new JButton();
        b17.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b17, gbc);
        b25 = new JButton();
        b25.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b25, gbc);
        b26 = new JButton();
        b26.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b26, gbc);
        b27 = new JButton();
        b27.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b27, gbc);
        b28 = new JButton();
        b28.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b28, gbc);
        b29 = new JButton();
        b29.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b29, gbc);
        b30 = new JButton();
        b30.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b30, gbc);
        b31 = new JButton();
        b31.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b31, gbc);
        b33 = new JButton();
        b33.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b33, gbc);
        b34 = new JButton();
        b34.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b34, gbc);
        b35 = new JButton();
        b35.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b35, gbc);
        b36 = new JButton();
        b36.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b36, gbc);
        b37 = new JButton();
        b37.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b37, gbc);
        b38 = new JButton();
        b38.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b38, gbc);
        b39 = new JButton();
        b39.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b39, gbc);
        b41 = new JButton();
        b41.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b41, gbc);
        b42 = new JButton();
        b42.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b42, gbc);
        b43 = new JButton();
        b43.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b43, gbc);
        b44 = new JButton();
        b44.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b44, gbc);
        b45 = new JButton();
        b45.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b45, gbc);
        b46 = new JButton();
        b46.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b46, gbc);
        b47 = new JButton();
        b47.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b47, gbc);
        b49 = new JButton();
        b49.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b49, gbc);
        b50 = new JButton();
        b50.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b50, gbc);
        b51 = new JButton();
        b51.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b51, gbc);
        b52 = new JButton();
        b52.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b52, gbc);
        b53 = new JButton();
        b53.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b53, gbc);
        b54 = new JButton();
        b54.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b54, gbc);
        b55 = new JButton();
        b55.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b55, gbc);
        b57 = new JButton();
        b57.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b57, gbc);
        b58 = new JButton();
        b58.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b58, gbc);
        b59 = new JButton();
        b59.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b59, gbc);
        b60 = new JButton();
        b60.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b60, gbc);
        b61 = new JButton();
        b61.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b61, gbc);
        b62 = new JButton();
        b62.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b62, gbc);
        b63 = new JButton();
        b63.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b63, gbc);
        b3 = new JButton();
        b3.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        boardPanel.add(b3, gbc);
        ScorePanel = new JPanel();
        ScorePanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gamePanel.add(ScorePanel, gbc);
        backBtn = new JButton();
        backBtn.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ScorePanel.add(backBtn, gbc);
        player1ScorePanel = new JPanel();
        player1ScorePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ScorePanel.add(player1ScorePanel, gbc);
        player1Label = new JLabel();
        player1Label.setText("Player1");
        player1ScorePanel.add(player1Label, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player1Score = new JLabel();
        player1Score.setText("Label");
        player1ScorePanel.add(player1Score, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player1Type = new JLabel();
        player1Type.setText("Label");
        player1ScorePanel.add(player1Type, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player2ScorePanel = new JPanel();
        player2ScorePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ScorePanel.add(player2ScorePanel, gbc);
        player2Label = new JLabel();
        player2Label.setText("Player 2");
        player2ScorePanel.add(player2Label, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player2Score = new JLabel();
        player2Score.setText("Label");
        player2ScorePanel.add(player2Score, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        player2Type = new JLabel();
        player2Type.setText("Label");
        player2ScorePanel.add(player2Type, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return gamePanel;
    }


}
