package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.SQLException;

public class DeleteUserCommand extends Command {

    public DeleteUserCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            String idUser = String.valueOf(jsonNode.get("id"));
            DAOFactory.getInstance().getUserDAO().deleteUserById(idUser);
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 200);
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
