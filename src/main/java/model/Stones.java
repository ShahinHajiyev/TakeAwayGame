package model;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing the possible orientations of stones.
 */
public class Stones {

     public static List<Integer> boxes =Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

     public void initializeBoxes(){
          for (int i = 0; i < boxes.size(); i++){
               boxes.set(i,1);
          }
     }
}
