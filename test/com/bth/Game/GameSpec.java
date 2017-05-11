package com.bth.Game;

import com.bth.App.StateManager;
import com.bth.Game.Cave.Cave;
import com.bth.Game.Cave.CaveHandler;
import com.bth.Game.Game;
import com.bth.Game.GameState;
import com.bth.Game.Item.Item;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Item.Potion;
import com.bth.Game.Item.Sword;
import com.bth.Game.Player.Backpack;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.Interpreter;
import com.bth.Game.Util.Observer.Action;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(OleasterRunner.class)
public class GameSpec {
    private Game game;
    private ByteArrayOutputStream os;
    private PrintStream stdout = System.out;
    private Cave mockCave;
    private CaveHandler mockCaveHandler;
    {
        describe("handleOpenBackpack", () -> {
            beforeEach(() -> {
                os = new ByteArrayOutputStream();
                System.setOut(new PrintStream(os));
                os.reset();
//                System.setIn(new ByteArrayInputStream("1".getBytes()));
                game = new Game();
            });

            it ("should let the user know if backpack is empty", () -> {
                assertFalse(game.handleOpenBackpack());
            });

            it ("should have an empty backpack on start", () -> {
                game.handleOpenBackpack();

                assertEquals(game.getPlayer().getBackpack().isEmpty(), true);
            });

            it ("should call open() on the backpack if there are items", () -> {
                System.setIn(new ByteArrayInputStream("close".getBytes()));
                Player mockPlayer = mock(Player.class);
                Backpack mockBackpack = mock(Backpack.class);
                Interpreter mockInterpreter = mock(Interpreter.class);

                when(mockPlayer.getBackpack()).thenReturn(mockBackpack);
                when(mockBackpack.isEmpty()).thenReturn(false);
                when(mockPlayer.getBackpack().isEmpty()).thenReturn(false);
                when(mockBackpack.selectItemDialog()).thenReturn("close");
                when(mockInterpreter.interpretString("close")).thenReturn(Action.DO_NOTHING);

                game.setPlayer(mockPlayer);
                game.setInterpreter(mockInterpreter);


                game.handleOpenBackpack();
                verify(mockBackpack, times(1)).open();
            });




        });

        describe("enterNextCave", () -> {
            beforeEach(() -> {
                System.setOut(new PrintStream(os));
                os.reset();
                game = new Game();

                mockCave = mock(Cave.class);
                mockCaveHandler = mock(CaveHandler.class);
                game.setCaveHandler(mockCaveHandler);

                when (mockCaveHandler.getNextCave()).thenReturn(mockCave);
            });

            it ("should set the current cave to the cave", () -> {
                game.enterNextCave();

                assertEquals(game.getCurrentCave(), mockCave);
            });

            it ("should add the items from current cave to the item handler", () -> {
                ItemHandler mockItemHandler = mock(ItemHandler.class);
                ArrayList<Item> caveItems = new ArrayList<>();
                caveItems.add(new Potion());
                caveItems.add(new Sword());

                game.setItemHandler(mockItemHandler);
                game.setCurrentCave(mockCave);
                mockCave.setItems(caveItems);

                game.enterNextCave();
                verify(mockItemHandler).addItems(mockCave.getItems());
            });

            it ("should set the player position to 0, 0", () -> {
                game.enterNextCave();

                int x = game.getPlayerPos().get('x');
                int y = game.getPlayerPos().get('y');

                assertTrue(x == 0 && y == 0);
            });
        });

        describe("endGameSession", () -> {
            it ("should call endGameSession() on the game state", () -> {
                GameState mockGameState = mock(GameState.class);
                game.setGameState(mockGameState);

                game.endGameSession();

                verify(mockGameState, times(1)).endGameSession();
            });
        });

        describe("moveDirection", () -> {
            beforeEach(() -> {
                game = new Game();
                mockCave = mock(Cave.class);
                game.setCurrentCave(mockCave);
            });

            it ("should return true if player have moved", () -> {
                HashMap<Character, Integer> playerPos = new HashMap<>();
                playerPos.put('x', 0);
                playerPos.put('y', 0);

                game.setPlayerPos(playerPos);

                when(mockCave.validateMove(Movement.NORTH, game.getPlayerPos())).thenReturn(true);

                assertEquals(game.moveDirection(Movement.NORTH), true);
            });

            it ("should update player Y position with 1 if valid move", () -> {
                int startY = 0;
                int startX = 0;
                HashMap<Character, Integer> playerPos = new HashMap<>();
                playerPos.put('x', startX);
                playerPos.put('y', startY);

                game.setPlayerPos(playerPos);

                when(mockCave.validateMove(Movement.NORTH, game.getPlayerPos())).thenReturn(true);

                game.moveDirection(Movement.NORTH);

                int newPosY = game.getPlayerPos().get('y');

                assertEquals(newPosY, startY - 1);
            });
        });
    }
}
