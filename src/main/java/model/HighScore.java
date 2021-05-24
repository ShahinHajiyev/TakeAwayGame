package model;

import lombok.Data;


/**
 * Class representing the high score of players {@code tableView}.
 */
@Data
public class HighScore {
    private String player;
    private int score;

}
