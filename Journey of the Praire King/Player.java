import javax.swing.*;

import java.awt.*;

import java.util.List;
import java.util.ArrayList;

public class Player {

    public static final int TOP_PLAYER = 0;
    public static final int BOTTOM_PLAYER = 1;

    private final int WIDTH = 65;
    private final int HEIGHT = 80;

    private final Board board;

    private int playerNumber;

    private int xCor;
    private int yCor;

    private int shift;

    ImageIcon unscaled;
    Image scale;
    ImageIcon character;

    private List<Bullet> bullets;
    private boolean remove;
    private Bullet bulletRemoved;

    private int health;

    public Player(Board board, int playerNumber) {
        this.board = board;

        this.playerNumber = playerNumber;

        xCor = board.getWidth() / 2;

        if (playerNumber == 0) {
            yCor = 50;
        } else {
            yCor = board.getHeight() - (HEIGHT + 50);
        }

        bullets = new ArrayList<Bullet>();

        health = WIDTH;

        newImage();
    }

    private void newImage() {
        unscaled = new ImageIcon("Player.jpg");
        scale = unscaled.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        character = new ImageIcon(scale);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        g2d.drawLine(xCor, yCor, xCor + health, yCor);

        character.paintIcon(board, g, xCor, yCor);

        for (Bullet i : bullets) {
            i.draw(g);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(xCor, yCor, WIDTH, HEIGHT);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void update() {
        if (xCor + WIDTH < board.getWidth() && xCor > 0) {
            xCor += shift;
        } else if (xCor + WIDTH >= board.getWidth()) {
            xCor -= 1;
        } else if (xCor <= 0) {
            xCor += 1;
        }

        for (Bullet i : bullets) {
            i.update();
            if (remove) {
                bulletRemoved = i;
                remove = false;
            }
        }

        bullets.remove(bulletRemoved);
    }

    public void shoot() {
        if (playerNumber == TOP_PLAYER) {
            bullets.add(new Bullet(board, this, xCor + (WIDTH / 2), yCor + HEIGHT, Bullet.DOWN_DIRECTION));
        }

        if (playerNumber == BOTTOM_PLAYER) {
            bullets.add(new Bullet(board, this, xCor + (WIDTH / 2), yCor, Bullet.UP_DIRECTION));
        }
    }

    public void removeBullet() {
        remove = true;
    }

    public void setBulletRemoved(Bullet bullet) {
        bulletRemoved = bullet;
    }

    public void lowerHealth() {
        health -= WIDTH / 10;
        if (health <= 0) {
            
            health = WIDTH;
            
            bullets.clear();

            if (playerNumber == TOP_PLAYER) {
                board.playerTwoWins();
            } else {
                board.playerOneWins();
            }
        }
    }

    public void moveLeft() {
        shift = -2;
    }

    public void moveRight() {
        shift = 2;
    }

    public void stop() {
        shift = 0;
    }
}