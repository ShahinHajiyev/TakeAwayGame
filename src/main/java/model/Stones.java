package model;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing the possible orientations of stones.
 */
public class Stones {

     /**
      * List represents the possible orientation of stones
      */
     public static List<Integer> boxes =Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);


     /**
      * Initializing boxes.
      */
     public void initializeBoxes(){
          for (int i = 0; i < boxes.size(); i++){
               boxes.set(i,1);
          }
     }
}
