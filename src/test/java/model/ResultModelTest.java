package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultModelTest {
    ResultModel resultModel = new ResultModel();
    PlayerModel playerModel = new PlayerModel();

    @Test
    void modifyMoves() {
        resultModel.modifyMoves(playerModel,1);
        assertEquals(1,resultModel.getFirstPlayerMoves());

        playerModel.setFirstPlayerTurn(false);
        resultModel.modifyMoves(playerModel,2);
        assertEquals(2,resultModel.getSecondPlayerMoves());
    }
}