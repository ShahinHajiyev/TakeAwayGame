package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {

    PlayerModel playerModel = new PlayerModel();

    @Test
    void switchPlayer() {
        assertFalse(playerModel.switchPlayer());
        assertTrue(playerModel.switchPlayer());
    }
}