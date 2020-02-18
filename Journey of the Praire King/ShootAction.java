import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ShootAction extends AbstractAction {

    private Player player;

    public ShootAction(Player player) {
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.shoot();
    }
}