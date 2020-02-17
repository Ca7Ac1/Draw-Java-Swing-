import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    private final int WIDTH = 700;
    private final int HEIGHT = 700;

    private final int DELAY = 5;

    private Game game;
    private Timer timer;

    private Player playerOne;
    private Player playerTwo;

    public Board(Game game) {
        this.game = game;

        timer = new Timer(DELAY, this);

        playerOne = new Player(this, Player.TOP_PLAYER);
        playerTwo = new Player(this, Player.BOTTOM_PLAYER);

        addKeyListener(new KeyChecker(this));
        timer.start();

        setFocusable(true);
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        playerOne.draw(g);
        playerTwo.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerOne.update();
        playerTwo.update();
        repaint();
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public Player getPlayer(int playerNumber) {
        if (playerNumber == 0) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public void playerOneWins() {
        if (JOptionPane.showConfirmDialog(game.getFrame(), "Player 2 Wins. Would you like to play again", "Game Over",
                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
            game.restart();
        } else {
            game.end();
        }
    }

    public void playerTwoWins() {
        if (JOptionPane.showConfirmDialog(game.getFrame(), "Player 1 Wins. Would you like to play again", "Game Over",
                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
            game.restart();
        } else {
            game.end();
        }
    }

    public void restart() {
        
    }
}