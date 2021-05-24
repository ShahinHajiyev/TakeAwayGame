package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.GameState;
import model.PlayerModel;
import model.ResultModel;
import model.Stones;
import org.tinylog.Logger;
import util.JsonPersist;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import static model.Stones.boxes;


public class GameController {

    private PlayerModel playerModel = new PlayerModel();
    private ResultModel resultModel = new ResultModel();
    private GameState gameState = new GameState();
    private Stones stones = new Stones();

    @FXML
    private Label gameOver;

    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label player1MoveLabel;

    @FXML
    private Label player2MoveLabel;

    @FXML
    private Label adjacentLabel;


     public void start(String player1, String player2){
         resultModel.setFirstPlayerName(player1);
         resultModel.setSecondPlayerName(player2);
         name1.setText(resultModel.getFirstPlayerName());
         name2.setText(resultModel.getSecondPlayerName());
         player1MoveLabel.setText(name1.getText() + "'s move");
     }


    private Image stone = new Image(getClass().getResource("/pictures/stoneimage.png").toExternalForm());


    public void pressKey(MouseEvent mouseEvent) {
        var column = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();

        playerModel.getBoxList().add(column);
        playerModel.getBoxImageList().add(im);
    }

    @FXML
    public void initialize() {
        resultModel.setTime(Instant.now());
        int random = gameState.initialState();
        Logger.info("Empty box is assigned to {}th box",random);
    }




    public void openBox(int column, ImageView imageView){
        if (!gameState.isSolved(boxes)) {
            if (boxes.get(column) == 1) {
                imageView.setImage(stone);
                boxes.set(column, 2);
                if (gameState.isSolved(boxes)) {
                    gameOver();
                    adjacentLabel.setText("GAME IS FINISHED!");
                    Logger.info("Game is finished, press finish to view results.");
                }
            }
            else if (boxes.get(column) == 0) {
                imageView.setImage(null);
            }
        }
        else {
            gameOver();
            adjacentLabel.setText("GAME IS FINISHED!");
            Logger.info("Game is finished, press finish to view results.");
        }
    }

    public void gameOver(){
        if(playerModel.isFirstPlayerTurn()){
            gameOver.setText(name1.getText() + " won the game!");
            resultModel.setWinner(name1.getText());
        }
        else{
            gameOver.setText(name2.getText() + " won the game!");
            resultModel.setWinner(name2.getText());
        }
    }

    public void checkBoxesAndOpen(List<Integer> boxList, List<ImageView> boxImageList){
        if(boxList.size() > 2){
            adjacentLabel.setText("Cannot select more than 2 boxes");
            Logger.warn("To choose more than 2 boxes is forbidden!");
            clearBoxList(boxList,boxImageList);
        }
        else if(boxList.size() == 0){
            adjacentLabel.setText("Please, choose box or 2 adjacent boxes");
            Logger.warn("Select a box or boxes!");
        }
        else if(boxList.size() == 2){
            if(Math.abs(boxList.get(0) - boxList.get(1)) == 1){
                openBox(boxList.get(0), boxImageList.get(0));
                openBox(boxList.get(1), boxImageList.get(1));
                resultModel.modifyMoves(playerModel,2);
                clearBoxList(boxList,boxImageList);
                adjacentLabel.setText("");
                switchPlayer(playerModel.switchPlayer());

            }
            else {
                clearBoxList(boxList,boxImageList);
                adjacentLabel.setText("Boxes should be adjacent");
                Logger.warn("Boxes are not adjacent!");
            }
        }
        else if(boxList.size() == 1) {
            openBox(boxList.get(0), boxImageList.get(0));
            resultModel.modifyMoves(playerModel,1);
            clearBoxList(boxList,boxImageList);
            adjacentLabel.setText("");
           switchPlayer(playerModel.switchPlayer());
        }
    }

    public void switchPlayer(boolean playerTurn){
       if(!gameState.isSolved(boxes)) {
           if (playerTurn) {
               player2MoveLabel.setText("");
               player1MoveLabel.setText(name1.getText() + "'s move");
           } else {
               player1MoveLabel.setText("");
               player2MoveLabel.setText(name2.getText() + "'s move");
           }
       }
    }

    public void openBoxAction(ActionEvent actionEvent) {
        checkBoxesAndOpen(playerModel.getBoxList(),playerModel.getBoxImageList());
    }

    public void finishGame(ActionEvent actionEvent) throws IOException {

        clearBoxList(playerModel.getBoxList(),playerModel.getBoxImageList());
        stones.initializeBoxes();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topFive.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        JsonPersist.writer(resultModel);

    }

    public void clearBoxList(List<Integer> boxList, List<ImageView> boxImageList){
        boxList.clear();
        boxImageList.clear();
    }
}



