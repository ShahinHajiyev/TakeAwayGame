package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import model.GameState;
import model.Stones;


import java.time.Instant;


public class GameController {
    public GameState gameState = new GameState();
    private String firstPlayer;
    private String secondPlayer;
    private Instant beginGame;



    @FXML
    private ListView<String> listView;

    @FXML
    private Pane Pane;

    @FXML
    private Button start;

    @FXML
    private Button Reset;

    @FXML
    private Label error;

    @FXML
    private Button clearButton;

    @FXML
    ObservableList<String> list;




    public void initdata(String firstPlayer, String secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        //playerNameLabel.setText("Current user: " + this.firstPlayer);
    }

    public void clickStart(ActionEvent actionEvent) {
        Integer[] array = listView.getSelectionModel().getSelectedIndices().toArray(new Integer[2]); // Mene lazimdi ki sadece 2 dene index alsin
                                                                                                    //bu ise 0 dan []  <-- bura hansi ededi yazirsansa onu alir ye qeder olan indexleri goturur
                                                                             //arraya yigir ve clickStart duymesi basilanda yigdiqlarini "O" edir.
        for(int i = 0; i<array.length; i++) {
            if (array.length > 2) {
                error.setText("Choose 1 or 2 boxes");
            }
           listView.getItems().set(i, "O");

      }
    }

    public void reset(ActionEvent actionEvent) {
        list = FXCollections.observableArrayList(gameState.initialState(Stones.boxes));

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getItems().setAll(list);      //burda ise reset basanda sehne yeniden yigilmalidir, bu command ise evvelki sehneni yadinda saxlayir, bir nov deyismir, ustune elave edir

    }


  /*  public void clear(ActionEvent actionEvent) {
        if (listView.getItems().contains("S")) {
            //listView.getItems().seta
        }

    }   */
}
