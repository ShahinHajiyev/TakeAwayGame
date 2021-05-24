package model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    GameState gameState = new GameState();

    @Test
    void initialState() {
        int random = gameState.initialState();
        assertEquals(0,Stones.boxes.get(random));
    }

    @Test
    void isSolved() {
        List<Integer> list = Arrays.asList(2,2,2,2,2,2,2,2,2,2,2,2,2,2,0);
        assertTrue(gameState.isSolved(list));

        list.set(0,1);
        assertFalse(gameState.isSolved(list));
    }
}