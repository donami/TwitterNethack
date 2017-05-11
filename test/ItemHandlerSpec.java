import com.bth.Game.Item.Item;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Item.Potion;
import com.bth.Game.Item.Sword;
import com.bth.Game.Player.Backpack;
import com.bth.Game.Player.Player;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


@RunWith(OleasterRunner.class)
public class ItemHandlerSpec {
    private ItemHandler itemHandler;
    private Backpack backpack;
    {
        describe("itemSaveDialog", () -> {
            beforeEach(() -> {
                this.itemHandler = new ItemHandler();
                this.backpack = new Backpack(new Player());
                OutputStream os = new ByteArrayOutputStream();
                System.setOut(new PrintStream(os));
            });

            it ("should return true if 1 is entered", () -> {
                Item item = new Potion();
                System.setIn(new ByteArrayInputStream("1".getBytes()));

                assertEquals(itemHandler.itemSaveDialog(item, backpack), true);
            });
/*
            it ("should return true if 2 is entered", () -> {
                Item item = new Potion();
                System.setIn(new ByteArrayInputStream("2".getBytes()));

                assertEquals(itemHandler.itemSaveDialog(item, backpack), true);
            });

            it ("should return false if 3 is entered", () -> {
                Item item = new Potion();
                System.setIn(new ByteArrayInputStream("3".getBytes()));

//                assertEquals(itemHandler.itemSaveDialog(item, backpack), false);
            });
*/
            /*
            it ("should print command menu once", () -> {
                PrinterInterface mockPrinter = mock(Decision.class);
//                itemHandler.setPrinter(mockPrinter);

                ArrayList<EventInterface> menu = new ArrayList<>();
                menu.add(Action.SAVE_ITEM);
                menu.add(Action.USE_ITEM);
                menu.add(Action.DO_NOTHING);

                itemHandler.itemSaveDialog(new Potion(), backpack);
                verify(mockPrinter, times(1)).printCommandMenu(menu);
            });*/
        });

        describe("useItem()", () -> {
            it ("should remove the item after use", () -> {
                Item mockItem = mock(Potion.class);

                this.itemHandler.addItem(mockItem);

                this.itemHandler.useItem(mockItem);

                assertEquals(this.itemHandler.contains(mockItem), false);
            });

            it ("should call the use() method on the item", () -> {
                Item mockItem = mock(Potion.class);

                this.itemHandler.addItem(mockItem);

                this.itemHandler.useItem(mockItem);

                verify(mockItem, times(1)).use();
            });

        });

        describe("getItemByCode()", () -> {
            it ("should return null if item is not found", () -> {
                Item mockItem = mock(Potion.class);

                when(mockItem.getName()).thenReturn("Potion");

                this.itemHandler.addItem(mockItem);

                assertNull(this.itemHandler.getItemByCode("sword"));
            });

            it ("should return the correct item if it is found", () -> {
                Item mockPotion = mock(Potion.class);
                Item mockSword = mock(Sword.class);

                when(mockPotion.getName()).thenReturn("Potion");
                when(mockSword.getName()).thenReturn("Sword");

                this.itemHandler.addItem(mockPotion);
                this.itemHandler.addItem(mockSword);

                Item item = this.itemHandler.getItemByCode("sword");

                assertEquals(item, mockSword);
            });
        });
    }
}
