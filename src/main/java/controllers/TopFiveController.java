package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HighScore;
import model.ResultModel;
import org.tinylog.Logger;
import util.JsonPersist;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopFiveController {

    @FXML
    private TableView topFiveTable;

    @FXML
    private TableColumn<ResultModel, String> player;
    @FXML
    private TableColumn<ResultModel, Integer> score;

    @FXML
    public void initialize(){
        Logger.debug("Loading top 5 scores...");
        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        List<ResultModel> resultModelList;
        ObservableList<HighScore> highScores = FXCollections.observableArrayList();

        File file = JsonPersist.reader(this);

        try{
            if(file.length() != 0){
                resultModelList = mapper.readValue(file, new TypeReference<List<ResultModel>>() {
                });
                Map<String,Long> map = resultModelList.stream()
                        .collect(Collectors.groupingBy(ResultModel::getWinner,Collectors.counting()));

                map.entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                        .limit(5)
                        .forEach(entry -> {
                            HighScore highScore = new HighScore();
                            highScore.setPlayer(entry.getKey());
                            highScore.setScore(entry.getValue().intValue());
                            highScores.add(highScore);
                        });
            }
            topFiveTable.setItems(highScores);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
