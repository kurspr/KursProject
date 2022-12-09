package by.bsuir.server.dao;

import by.bsuir.server.entity.MinusOrPlus;

import java.sql.SQLException;
import java.util.List;

public interface MinusOrPlusDAO {
    void deleteMinusOrPlus(String minOrPl, String id, String mess) throws SQLException;

    void saveMinusOrPlus(MinusOrPlus minusOrPlus) throws SQLException;

    List<MinusOrPlus> loadListMinus(String idUser, String mess) throws SQLException;

    List<MinusOrPlus> loadListPlus(String idUser, String mess) throws SQLException;
}
