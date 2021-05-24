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

    private Instant time;

    private  String winner;
}
