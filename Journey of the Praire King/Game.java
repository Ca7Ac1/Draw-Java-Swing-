import javax.swing.*;

public class Game {

    private JFrame frame;
    private Board board;

    public Game() {
        frame = new JFrame("Praire King Multiplayer");
        board = new Board(this);

        initializeFrame();
    }

    private void initializeFrame() {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Board(this));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void restart() {
        board.restart();

        frame.dispose();
        frame = null;
        frame = new JFrame("Praire King Multiplayer");

        initializeFrame();
    }

    public void end() {
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
}