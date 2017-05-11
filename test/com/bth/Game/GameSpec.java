package com.bth.Game;

import com.bth.App.StateManager;
import com.bth.Game.Game;
import com.bth.Game.GameState;
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

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(OleasterRunner.class)
public class GameSpec {
    private Game game;
    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private PrintStream stdout = System.out;
    {
        describe("handleOpenBackpack", () -> {
            beforeEach(() -> {

                System.setOut(new PrintStream(os));
                os.reset();
//                System.setIn(new ByteArrayInputStream("1".getBytes()));
                game = new Game();
            });

            it ("should let the user know if backpack is empty", () -> {
                game.handleOpenBackpack();

                assertEquals(os.toString().trim(), "Your backpack is empty");

//                System.setOut(stdout);
//                System.out.println(os.toString());


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
    }
}
