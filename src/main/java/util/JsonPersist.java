package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controllers.GameController;
import controllers.TopFiveController;
import model.ResultModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonPersist {

    public static void writer(ResultModel resultModel) {
        File file = new File(GameController.class.getClassLoader().getResource("data.json").getFile());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            List<ResultModel> resultModelList = new ArrayList<>();
            if (file.length() != 0) {
                resultModelList = mapper.readValue(file, new TypeReference<List<ResultModel>>() {
                });
            }
            resultModelList.add(resultModel);
            writer.writeValue(file, resultModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File reader(TopFiveController topFiveController) {
        return new File(topFiveController.getClass().getClassLoader().getResource("data.json").getFile());
    }
}

