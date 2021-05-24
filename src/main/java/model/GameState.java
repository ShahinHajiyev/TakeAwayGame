package model;


import java.util.List;
import java.util.Random;
import static model.Stones.boxes;


/**
 * Class representing the state of the game.
 */
public class GameState {

    /**
     * The initial state of Stones. When the game starts, this selects one index of boxes randomly
     * and makes it empty.
     */
    public int initialState() {

        Random rand = new Random();
        int random = rand.nextInt(15);
        boxes.set(random, 0);

        return random;
    }

    /**
     * Checks whether the game is solved
     * @return true or false based on count
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
