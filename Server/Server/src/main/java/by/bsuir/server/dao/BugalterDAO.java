package by.bsuir.server.dao;

import by.bsuir.server.entity.AuthEntity;

import java.sql.*;

public class BugalterDAO {

    private static final String GET_BUGALTER_BY_USERNAME = "SELECT * FROM mydb.bugalters t where t.username = '%s'";


    public static AuthEntity checkBugalter(AuthEntity authEntity)
    {
        ResultSet rs = null;
        Connection connection = null;
        AuthEntity responseEntity = new AuthEntity();
        String resultQuery = String.format(GET_BUGALTER_BY_USERNAME,authEntity.getUsername()) ;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(resultQuery);
            while (rs.next()) {
                responseEntity.setUsername(rs.getString(2));
                responseEntity.setPassword(rs.getString(3));
                responseEntity.setPermission("bugalter");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
