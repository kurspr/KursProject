package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import by.bsuir.server.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.SQLException;

public class UpdateUserCommand extends Command {
    public UpdateUserCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        User user;
        try {
            user = mapper.readValue(jsonString, User.class);
            DAOFactory.getInstance().getUserDAO().updateUser(user);
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 200);
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
