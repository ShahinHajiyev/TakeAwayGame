package model;

import lombok.Data;

import java.time.Instant;

/**
 * Class representing the result state of the players.
 */
@Data
public class ResultModel {

    private String firstPlayerName;

    private String secondPlayerName;

    private int firstPlayerMoves = 0;

    private int secondPlayerMoves = 0;

    private Instant time;

    private  String winner;


    /**
     * Modifies moves of player.
     * Checking whose turn it is.
     * Increments moves of player for highScore table.
     * @param playerModel,  instance of player model.
     * @param moves moves taken by players.
     */
    public void modifyMoves(PlayerModel playerModel,int moves){
        if(playerModel.isFirstPlayerTurn()){
            firstPlayerMoves += moves;
        }
        else {
            secondPlayerMoves += moves;
        }
    }
}
