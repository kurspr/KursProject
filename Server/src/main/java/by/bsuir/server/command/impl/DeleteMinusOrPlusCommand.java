package by.bsuir.server.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.SQLException;

public class DeleteMinusOrPlusCommand extends Command {
    public DeleteMinusOrPlusCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            String idUser = String.valueOf(jsonNode.get("id"));
            String mess = String.valueOf(jsonNode.get("mess"));
            MinusOrPlusCommand.dellMinOrPlus(idUser, mess);
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 200);
            return node.toString();
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
