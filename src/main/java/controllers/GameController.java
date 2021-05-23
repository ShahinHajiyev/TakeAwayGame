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
import model.GameState;
import org.tinylog.Logger;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static model.Stones.boxes;


public class GameController {
    public GameState gameState = new GameState();
    private String firstPlayer;
    private String secondPlayer;
    private Instant beginGame;
    private List<Image> stones;

    private boolean player1Move = true;

    private List<Integer> boxList = new ArrayList<>();

    private List<ImageView> boxImageList = new ArrayList<>();

    @FXML
    private GridPane gameGrid;

    @FXML
    private Label gameOver;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private Label player1MoveLabel;

    @FXML
    private Label player2MoveLabel;

    @FXML
    private Button finish;




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


   /* public void pressKey(MouseEvent mouseEvent) {

        var column = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();



            if (!gameState.isSolved(boxes)) {

                if (boxes.get(column) == 1) {

                    im.setImage(stone);
                    boxes.set(column, 2);
                    if (gameState.isSolved(boxes)) {
                        System.out.println("Game is finished");
                        gameOver.setText("GAME IS FINISHED!");
                    }
                }
                    else if (boxes.get(column) == 0) {
                        im.setImage(null);
                    }
            }
                else {
                    System.out.println("Game is finished!");
                    gameOver.setText("GAME IS FINISHED!");
                }
        }
*/
    public void pressKey(MouseEvent mouseEvent) {
        var column = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();

        boxList.add(column);
        boxImageList.add(im);
    }

    @FXML
    public void initialize() {


        name1.textProperty().bind(Bindings.concat(playerName1));
        name2.textProperty().bind(Bindings.concat(playerName2));

        gameState.initialState();
        player1MoveLabel.setText(name1.getText() + "'s move");
    }


    public void Reset(ActionEvent actionEvent) {



        Logger.info("Game reset");
    }

    public void finishGame(ActionEvent actionEvent) throws IOException {
        //if (!gameState.isSolved(boxes)) {
            // gameResultDao.persist(getResult());
         //    }

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/topFive.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("Finished game, press finish to view results.");
        }

    public void openBoxAction(ActionEvent actionEvent) {
        checkBoxesAndOpen(boxList,boxImageList);
    }

    public void checkBoxesAndOpen(List<Integer> boxList, List<ImageView> boxImageList){
        if(boxList.size() > 2){
            System.out.println("Cannot select 3 or more boxes");
            clearBoxList(boxList,boxImageList);
        }
        else if(boxList.size() == 0){
            System.out.println("Please choose the box or boxes");
        }
        else if(boxList.size() == 2){
            if(Math.abs(boxList.get(0) - boxList.get(1)) == 1){
                openBox(boxList.get(0), boxImageList.get(0));
                openBox(boxList.get(1), boxImageList.get(1));
                clearBoxList(boxList,boxImageList);
                switchPlayer();
            }
            else {
                System.out.println("Boxes should be adjacent");
                clearBoxList(boxList,boxImageList);
            }
        }
        else if(boxList.size() == 1) {
            openBox(boxList.get(0), boxImageList.get(0));
            clearBoxList(boxList,boxImageList);
            switchPlayer();
        }
    }

    private void switchPlayer() {
        if(player1Move){
            player1Move  = false;
            player1MoveLabel.setText("");
            player2MoveLabel.setText(name2.getText() + "'s move");
        }
        else {
            player1Move = true;
            player2MoveLabel.setText("");
            player1MoveLabel.setText(name1.getText() + "'s move");
        }
    }

    public void openBox(int column, ImageView imageView){
        if (!gameState.isSolved(boxes)) {
            if (boxes.get(column) == 1) {
                imageView.setImage(stone);
                boxes.set(column, 2);
                if (gameState.isSolved(boxes)) {
                    System.out.println("Game is finished");
                    gameOver.setText("GAME IS FINISHED!");
                }
            }
            else if (boxes.get(column) == 0) {
                imageView.setImage(null);
            }
        }
        else {
            System.out.println("Game is finished!");
            gameOver.setText("GAME IS FINISHED!");
        }
    }

    public void clearBoxList(List<Integer> boxList, List<ImageView> boxImageList){
        boxList.clear();
        boxImageList.clear();
    }
}



