package model;



import java.util.Random;

public class GameState {




    public String[] initialState(String[] array){

           Random rand = new Random();
           int random = rand.nextInt(15);

           for (int i = 0; i<array.length; i++) {
                 array[random] = "O";
           }
           return array;
       }

       private boolean isValid(String[] a){
           if(a==null || a.length != 15){
               return false;
           }
          return true;
       }

       public boolean isSolved(String[] finalArray){
        for(int i = 0; i<finalArray.length; i++){
            if(finalArray[i] != "O") {
                return false;
            }
        }
        return true;
       }



}
