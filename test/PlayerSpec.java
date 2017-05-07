import com.bth.Game.Player.Backpack;
import com.bth.Game.Player.Player;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class PlayerSpec {
    private Player player;
    {
        describe("openBackpack", () -> {
            beforeEach(() -> {
                player = new Player();
            });
            it ("should set backpack status to open", () -> {
               Backpack backpack = player.openBackpack();
               assert(backpack.getOpen());
            });

            it ("should return the backpack", () -> {
                assert(player.openBackpack() instanceof Backpack);
            });
        });
    }

}
