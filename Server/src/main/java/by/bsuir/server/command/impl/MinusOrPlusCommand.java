package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import by.bsuir.server.entity.MinusOrPlus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MinusOrPlusCommand {


    public static List<MinusOrPlus> loadMinusOrPlus(String idUser, String mess) throws SQLException {
        List<MinusOrPlus> resultList = new ArrayList<>();
        resultList.addAll(DAOFactory.getInstance().getMinusOrPlusDAO().loadListMinus(idUser, mess));
        resultList.addAll(DAOFactory.getInstance().getMinusOrPlusDAO().loadListPlus(idUser, mess));
        return resultList;
    }

    public static void dellMinOrPlus(String idUser, String mess) throws SQLException {
        DAOFactory.getInstance().getMinusOrPlusDAO().deleteMinusOrPlus("minus", idUser, mess);
        DAOFactory.getInstance().getMinusOrPlusDAO().deleteMinusOrPlus("plus", idUser, mess);
    }
}
