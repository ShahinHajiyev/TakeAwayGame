package model;

import javafx.scene.image.ImageView;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the dynamic state of the players inside game.
 */
@Data
public class PlayerModel {

    private boolean firstPlayerTurn;

    private List<Integer> boxList;

    private List<ImageView> boxImageList;

    public PlayerModel() {

        firstPlayerTurn = true;
        boxList = new ArrayList<>();
        boxImageList = new ArrayList<>();
    }

    /**
     * Switches player's turn.
     * Checking whose turn.
     * Switching player's turn.
     * Setting steps of player to 0.
     * @return player's turn.
     */
    public boolean switchPlayer(){
        if (firstPlayerTurn) {
            firstPlayerTurn = false;
        }
        else {
            firstPlayerTurn = true;
        }
        return firstPlayerTurn;
    }
}