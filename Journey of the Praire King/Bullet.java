import java.awt.*;

public class Bullet {

    public static final int UP_DIRECTION = -1;
    public static final int DOWN_DIRECTION = 1;

    private final int BULLET_SIZE = 10;
    private final int SHIFT = 2;

    private Board board;

    private Player player;

    private int xCor;
    private int yCor;

    private int direction;

    public Bullet(Board board, Player player, int xCor, int yCor, int direction) {
        this.board = board;
        this.player = player;

        this.xCor = xCor;
        this.yCor = yCor;
        this.direction = direction;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xCor, yCor, BULLET_SIZE, BULLET_SIZE);
    }

    public void update() {
        yCor += SHIFT * direction;
        checkBoundries();
    }

    private void checkBoundries() {
        if (yCor <= 0 || yCor >= board.getHeight() - BULLET_SIZE) {
            player.removeBullet(); 
        }

       if (getBounds().intersects(board.getPlayer(player.getPlayerNumber() - 1).getBounds())) {
            board.getPlayer(player.getPlayerNumber() - 1).lowerHealth();
            player.removeBullet();
       }

       for (Bullet i : board.getPlayer(player.getPlayerNumber() - 1).getBullets()) {
           if (i.getBounds().intersects(getBounds())) {
                board.getPlayer(player.getPlayerNumber() - 1).setBulletRemoved(i);
                player.removeBullet();
           }
       }
    }

    public Rectangle getBounds() {
        return new Rectangle(xCor, yCor, BULLET_SIZE, BULLET_SIZE);
    }
}