package by.bsuir.server.dao;

import by.bsuir.server.entity.MinusOrPlus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MinusOrPlusDAO {

    private static final String INSERT_MINUS_OR_PLUS = "INSERT INTO mydb.%s (`id_user`, `name`, `summ`, `mes`) VALUES ('%s', '%s', '%s', '%s')";

    public static void saveMinusOrPlus(MinusOrPlus minusOrPlus) throws SQLException {
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();

        String resultQuery = String.format(INSERT_MINUS_OR_PLUS, minusOrPlus.getMinusOrPlus(), minusOrPlus.getIdUser(), minusOrPlus.getName(), minusOrPlus.getSumm(), minusOrPlus.getMess());
        stmt.executeUpdate(resultQuery);

        stmt.close();
        connection.close();
    }

}
