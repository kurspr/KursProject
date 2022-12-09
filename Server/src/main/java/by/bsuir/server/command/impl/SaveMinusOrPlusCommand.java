package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import by.bsuir.server.dao.impl.MySQLMinusOrPlusDAO;
import by.bsuir.server.entity.MinusOrPlus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SaveMinusOrPlusCommand extends Command {
    public SaveMinusOrPlusCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        try {
            List<MinusOrPlus> list = Arrays.asList(mapper.readValue(jsonString, MinusOrPlus[].class));
            for (MinusOrPlus minusOrPlus : list) {
                if (minusOrPlus.getName().equals("") || minusOrPlus.getSumm().equals("")) {
                    continue;
                }
                DAOFactory.getInstance().getMinusOrPlusDAO().saveMinusOrPlus(minusOrPlus);
            }
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 200);
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException | SQLException e) {
            return "{\"status\":400}";
        }
    }
}
