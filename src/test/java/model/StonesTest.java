package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StonesTest {

    Stones stones = new Stones();

    @Test
    void initializeBoxes() {
        stones.initializeBoxes();

        for (int i = 0; i < Stones.boxes.size(); i++){
            assertEquals(1,Stones.boxes.get(i));
        }
    }
}