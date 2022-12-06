package by.bsuir.server.command;

import by.bsuir.server.dao.MinusOrPlusDAO;
import by.bsuir.server.entity.MinusOrPlus;

import java.sql.SQLException;
import java.util.List;

public class MinusOrPlusCommand {

    public static void saveMinusOrPlus(List<MinusOrPlus> list) throws SQLException {
        for(MinusOrPlus minusOrPlus:list)
        {
            MinusOrPlusDAO.saveMinusOrPlus(minusOrPlus);
        }

    }
}
