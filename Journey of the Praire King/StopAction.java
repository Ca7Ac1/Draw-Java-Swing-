import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class StopAction extends AbstractAction {

    private Player player;

    public StopAction(Player player) {
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.stop();
    }
}