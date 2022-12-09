package by.bsuir.server.command.impl;

import by.bsuir.server.entity.MinusOrPlus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public class LoadMinusOrPlusCommand extends Command {
    public LoadMinusOrPlusCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            String idUser = String.valueOf(jsonNode.get("id"));
            String mess = String.valueOf(jsonNode.get("mess"));
            List<MinusOrPlus> minusOrPlusList = MinusOrPlusCommand.loadMinusOrPlus(idUser, mess);
            JSONArray jsArray = new JSONArray(minusOrPlusList);
            return jsArray.toString();
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
