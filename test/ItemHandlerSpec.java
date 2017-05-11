import com.bth.Game.Item.*;
import com.bth.Game.Player.Backpack;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.Observer.Action;
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
    private ItemDialog mockItemDialog;
    {
        describe("itemSaveDialog", () -> {
            beforeEach(() -> {
                this.itemHandler = new ItemHandler();
                this.backpack = new Backpack(new Player());
                OutputStream os = new ByteArrayOutputStream();
                System.setOut(new PrintStream(os));

                this.mockItemDialog = mock(ItemDialog.class);

                itemHandler.setItemDialog(mockItemDialog);
            });

            it ("should return true if [save item] is selected", () -> {
                when(mockItemDialog.getChoice()).thenReturn(Action.SAVE_ITEM);

                Item item = new Potion();

                assertEquals(itemHandler.itemSaveDialog(item, backpack), true);
            });

            it ("should return true if [use item] is selected", () -> {
                when(mockItemDialog.getChoice()).thenReturn(Action.USE_ITEM);

                Item item = new Potion();

                assertEquals(itemHandler.itemSaveDialog(item, backpack), true);
            });

            it ("should return false if [close] is selected", () -> {
                when(mockItemDialog.getChoice()).thenReturn(Action.DO_NOTHING);

                Item item = new Potion();

                assertEquals(itemHandler.itemSaveDialog(item, backpack), false);
            });

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
