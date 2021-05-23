package controllers;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import model.GameState;
import model.results.GameResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;
import static model.Stones.boxes;

//@Slf4j
public class GameController {
    public GameState gameState = new GameState();
    private String firstPlayer;
    private String secondPlayer;
    private Instant beginGame;
    private List<Image> stones;
    private int stepCount;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Label gameOver;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private Label stepLabel;


    private StringProperty playerName1 = new SimpleStringProperty();
    private StringProperty playerName2 = new SimpleStringProperty();


    public void setPlayerName1(String name1) {
        this.playerName1.set(name1);
    }

    public void setPlayerName2(String name2) {
        this.playerName2.set(name2);
    }

    File file = new File("src/main/resources/pictures/stoneimage.png");    // it is forbidden
    Image stone = new Image(file.toURI().toString());


    public void pressKey(MouseEvent mouseEvent) {

        var column = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();

        if (!gameState.isSolved(boxes)) {
            stepCount++;
            if (boxes.get(column) == 1) {
                im.setImage(stone);
                boxes.set(column, 2);
                if (gameState.isSolved(boxes)) {
                    System.out.println("Game finished");
                    gameOver.setText("GAME IS FINISHED!");
                }
            } else if (boxes.get(column) == 0) {
                im.setImage(null);
            }
        } else {
            System.out.println("Game is finished!");
            gameOver.setText("GAME IS FINISHED!");
        }
    }

    @FXML
    public void initialize() {

        stepLabel.setText(String.valueOf(stepCount));

        stepCount=0;

        beginGame = Instant.now();

        name1.textProperty().bind(Bindings.concat(playerName1));
        name2.textProperty().bind(Bindings.concat(playerName2));

        gameState.initialState();
    }


    public void Reset(ActionEvent actionEvent) {

    }

    private GameResult getResult() {

        GameResult result = GameResult.builder()
                .player(playerName1.toString())
                .player(playerName2.toString())
                .solved(gameState.isSolved(boxes))
                .duration(Duration.between(beginGame, Instant.now()))
                .steps(stepCount)
                .build();
        return result;
    }

    public void finishGame(ActionEvent actionEvent) throws IOException {
        //if (!gameState.isSolved(boxes)) {
            // gameResultDao.persist(getResult());
         //    }

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/topFive.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            //log.info("Finished game, loading Top Five scene.");
        }
    }



