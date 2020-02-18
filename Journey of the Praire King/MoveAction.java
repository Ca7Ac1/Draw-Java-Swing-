import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MoveAction extends AbstractAction {

    private int direction;
    private Player player;

    public MoveAction(int direction, Player player) {
        this.direction = direction;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction == 0) {
            player.moveLeft();
        } else {
            player.moveRight();
        }
    }
}