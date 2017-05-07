import com.bth.Game.Item.Item;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Item.Potion;
import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Printer;
import com.bth.Game.Util.PrinterInterface;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(OleasterRunner.class)
public class ItemHandlerSpec {
    private ItemHandler itemHandler;
    {
        describe("itemSaveDialog", () -> {
            beforeEach(() -> {
                this.itemHandler = new ItemHandler();
                OutputStream os = new ByteArrayOutputStream();
                itemHandler.printer.setOut(new PrintStream(os));
            });

            it ("should return SAVE_ITEM if 1 is entered", () -> {
                Item item = new Potion();

                itemHandler.printer.setIn(new ByteArrayInputStream("1".getBytes()));
                assertEquals(itemHandler.itemSaveDialog(item), Action.SAVE_ITEM);
            });

            it ("should return USE_ITEM if 2 is entered", () -> {
                Item item = new Potion();

                itemHandler.printer.setIn(new ByteArrayInputStream("2".getBytes()));
                assertEquals(itemHandler.itemSaveDialog(item), Action.USE_ITEM);
            });

            it ("should return DO_NOTHING if 3 is entered", () -> {
                Item item = new Potion();

                itemHandler.printer.setIn(new ByteArrayInputStream("3".getBytes()));
                assertEquals(itemHandler.itemSaveDialog(item), Action.DO_NOTHING);
            });

            it ("should print command menu once", () -> {
                PrinterInterface mockPrinter = mock(Printer.class);
                itemHandler.setPrinter(mockPrinter);

                ArrayList<EventInterface> menu = new ArrayList<>();
                menu.add(Action.SAVE_ITEM);
                menu.add(Action.USE_ITEM);
                menu.add(Action.DO_NOTHING);

                itemHandler.itemSaveDialog(new Potion());
                verify(mockPrinter, times(1)).printCommandMenu(menu);
            });
        });
    }
}
