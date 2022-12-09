package by.bsuir.server.dao.impl;

import by.bsuir.server.dao.MinusOrPlusDAO;
import by.bsuir.server.entity.MinusOrPlus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMinusOrPlusDAO implements MinusOrPlusDAO {

    private static final String INSERT_MINUS_OR_PLUS = "INSERT INTO mydb.%s (`id_user`, `name`, `summ`, `mes`) VALUES ('%s', '%s', '%s', '%s')";
    private static final String SELECT_MINUS_BY_ID_AND_MESS = "SELECT * FROM mydb.minus m where m.id_user = %s and m.mes = %s";
    private static final String SELECT_PLUS_BY_ID_AND_MESS = "SELECT * FROM mydb.plus m where m.id_user = %s and m.mes = %s";
    private static final String DELETE_MINUS_OR_PLUS = "DELETE from mydb.%s p where p.id_user=%s and p.mes=%s";

    @Override
    public void deleteMinusOrPlus(String minOrPl, String id, String mess) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(DELETE_MINUS_OR_PLUS, minOrPl, id, mess);
        stmt.executeUpdate(resultQuery);
        stmt.close();
        connection.close();
    }

    @Override
    public void saveMinusOrPlus(MinusOrPlus minusOrPlus) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(INSERT_MINUS_OR_PLUS, minusOrPlus.getMinusOrPlus(), minusOrPlus.getIdUser(), minusOrPlus.getName(), minusOrPlus.getSumm(), minusOrPlus.getMess());
        stmt.executeUpdate(resultQuery);
        stmt.close();
        connection.close();
    }

    @Override
    public List<MinusOrPlus> loadListMinus(String idUser, String mess) throws SQLException {
        Connection connection = null;
        ResultSet rs;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(SELECT_MINUS_BY_ID_AND_MESS, idUser, mess);
        rs = stmt.executeQuery(resultQuery);
        List<MinusOrPlus> minusOrPlusList = new ArrayList<>();
        MinusOrPlus minusOrPlus;
        while (rs.next()) {
            minusOrPlus = new MinusOrPlus(idUser, rs.getString(2), rs.getString(3), mess, "minus");
            minusOrPlusList.add(minusOrPlus);
        }
        stmt.close();
        connection.close();
        return minusOrPlusList;
    }

    @Override
    public List<MinusOrPlus> loadListPlus(String idUser, String mess) throws SQLException {
        Connection connection = null;
        ResultSet rs;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(SELECT_PLUS_BY_ID_AND_MESS, idUser, mess);
        rs = stmt.executeQuery(resultQuery);
        List<MinusOrPlus> minusOrPlusList = new ArrayList<>();
        MinusOrPlus minusOrPlus;
        while (rs.next()) {
            minusOrPlus = new MinusOrPlus(idUser, rs.getString(2), rs.getString(3), mess, "plus");
            minusOrPlusList.add(minusOrPlus);
        }
        stmt.close();
        connection.close();
        return minusOrPlusList;
    }
}
