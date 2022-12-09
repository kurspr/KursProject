package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import by.bsuir.server.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.SQLException;

public class GetUserByIDCommand extends Command {

    public GetUserByIDCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        User user;
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            String idUser = String.valueOf(jsonNode.get("idUser"));
            user = DAOFactory.getInstance().getUserDAO().selectUserByID(idUser);
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 200);
            node.put("idUser", user.getId());
            node.put("fio", user.getFio());
            node.put("email", user.getEmail());
            node.put("phone", user.getPhone());
            node.put("address", user.getAddress());
            node.put("password", user.getFpassword());
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
