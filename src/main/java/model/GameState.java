package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static model.Stones.boxes;


/**
 * Class representing the state of the game.
 */
public class GameState {


    /**
     * The enum represents the turn between players.
     */
    public enum Player{
        PLAYER1,
        PLAYER2;

        public Player next(){
            return switch(this){
                case PLAYER1 -> PLAYER2;
                case PLAYER2 -> PLAYER1;
            };
        }
    }

    /**
     * This property is used to be passed to external users.
     */
    private ReadOnlyObjectWrapper<Player> currentPlayer = new ReadOnlyObjectWrapper<>();

    /**
     * Creates a {@code GameState} object representing that
     * first player starts the game
     */
    public GameState(){
        currentPlayer.set(Player.PLAYER1);
    }

    public Player getCurrentPlayer() {
        return currentPlayer.get();
    }

    public ReadOnlyObjectProperty<Player> currentPlayerProperty(){
        return currentPlayer.getReadOnlyProperty();
    }


    /**
     * The initial state of Stones. When the game starts, this selects one index of boxes randomly
     * and makes it empty.
     */
    public  void initialState() {

        Random rand = new Random();
        int random = rand.nextInt(15);
            boxes.set(random, 0);
        System.out.println(random);
    }

       public boolean isValid(List<Integer> a){
           if(a==null || a.size() != 15){
               return false;
           }
          return true;
       }

    /**
     * Checks whether the game is solved, if not returns false.
     * @param boxes is a list representing the initial configuration of the boxes
     */
    public boolean isSolved(List<Integer> boxes){
        int count = 0;
        for(int i = 0; i < boxes.size(); i++){
            if(boxes.get(i) == 2){
                count++;
            }
        }
        if(count == 14){
            return true;
        }
        else {
            return false;
        }
    }

}
