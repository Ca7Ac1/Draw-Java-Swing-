import java.awt.event.*;

public class KeyChecker implements KeyListener {

    Board board;

    boolean topPressed = false;
    boolean bottomPressed = false;

    public KeyChecker(Board board) {
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_S) {
            board.getPlayer(Player.TOP_PLAYER).moveLeft();
        }

        if (key == KeyEvent.VK_D) {
            board.getPlayer(Player.TOP_PLAYER).moveRight();
        }

        if (key == KeyEvent.VK_J) {
            board.getPlayer(Player.BOTTOM_PLAYER).moveLeft();
        }

        if (key == KeyEvent.VK_K) {
            board.getPlayer(Player.BOTTOM_PLAYER).moveRight();
        }

        if (key == KeyEvent.VK_E && !topPressed) {
            board.getPlayer(Player.TOP_PLAYER).shoot();
            topPressed = true;
        }

        if (key == KeyEvent.VK_U && !bottomPressed) {
            board.getPlayer(Player.BOTTOM_PLAYER).shoot();
            bottomPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
            board.getPlayer(Player.TOP_PLAYER).stop();
        }

        if (key == KeyEvent.VK_K || key == KeyEvent.VK_J) {
            board.getPlayer(Player.BOTTOM_PLAYER).stop();
        }

        if (key == KeyEvent.VK_E) {
            topPressed = false;
        }

        if (key == KeyEvent.VK_U) {
            bottomPressed = false;
        }
    }
}