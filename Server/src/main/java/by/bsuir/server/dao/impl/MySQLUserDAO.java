package by.bsuir.server.dao.impl;

import by.bsuir.server.dao.UserDAO;
import by.bsuir.server.entity.AuthEntity;
import by.bsuir.server.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {

    private static final String INSERT_NEW_USER = "INSERT INTO mydb.users (iduser, fio, email, phone, address, password) VALUES ('0', '%s', '%s', '%s', '%s', '%s')";

    private static final String SELECT_ALL_USERS = "SELECT * from mydb.users";

    private static final String SELECT_USER_BY_ID = "SELECT * from mydb.users u where u.iduser = %s";

    private static final String SELECT_USER_BY_EMAIL = "SELECT * from mydb.users u where u.email = %s";

    private static final String SELECT_USER_BY_EMAIL_AND_PASS = "SELECT * from mydb.users u where u.email ='%s' and u.password='%s'";

    private static final String UPDATE_USER_BY_ID = "UPDATE `mydb`.`users` SET `fio` = '%s', `email` = '%s', `phone` = '%s', `address` = '%s', `password` = '%s' WHERE (`iduser` = '%s')";

    private static final String DELETE_USER_BY_ID = "delete from mydb.users u where u.iduser = '%s'";

    @Override
    public void insertNewUser(User user) throws SQLException {
        Connection connection = null;
        String resultQuery = String.format(INSERT_NEW_USER, user.getFio(), user.getEmail(), user.getPhone(), user.getAddress(), user.getFpassword());
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(resultQuery);
        stmt.close();
        connection.close();
    }

    @Override
    public List<User> selectAllUsers() throws SQLException {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        User user;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(SELECT_ALL_USERS);
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt(1) + "");
            user.setFio(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPhone(resultSet.getString(4));
            user.setAddress(resultSet.getString(5));
            user.setFpassword(resultSet.getString(6));
            userList.add(user);
        }
        stmt.close();
        connection.close();
        return userList;
    }

    @Override
    public User selectUserByID(String idUser) throws SQLException {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        User user = new User();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(SELECT_USER_BY_ID, idUser);
        ResultSet resultSet = stmt.executeQuery(resultQuery);
        while (resultSet.next()) {
            user.setId(resultSet.getInt(1) + "");
            user.setFio(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPhone(resultSet.getString(4));
            user.setAddress(resultSet.getString(5));
            user.setFpassword(resultSet.getString(6));
        }
        resultSet.close();
        stmt.close();
        connection.close();
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection connection = null;
        String resultQuery = String.format(UPDATE_USER_BY_ID, user.getFio(), user.getEmail(), user.getPhone(), user.getAddress(), user.getFpassword(), user.getId());
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(resultQuery);
        stmt.close();
        connection.close();
    }

    @Override
    public void deleteUserById(String idUser) throws SQLException {
        Connection connection = null;
        String resultQuery = String.format(DELETE_USER_BY_ID, idUser);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(resultQuery);
        stmt.close();
        connection.close();
    }

    @Override
    public User selectUserByEmail(String email) throws SQLException {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        User user = new User();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        Statement stmt = connection.createStatement();
        String resultQuery = String.format(SELECT_USER_BY_EMAIL, email);
        ResultSet resultSet = stmt.executeQuery(resultQuery);
        while (resultSet.next()) {
            user.setId(resultSet.getInt(1) + "");
            user.setFio(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPhone(resultSet.getString(4));
            user.setAddress(resultSet.getString(5));
            user.setFpassword(resultSet.getString(6));
        }
        resultSet.close();
        stmt.close();
        connection.close();
        return user;
    }

    @Override
    public AuthEntity checkStudent(AuthEntity authEntity) {
        ResultSet rs = null;
        Connection connection = null;
        AuthEntity responseEntity = new AuthEntity();
        String resultQuery = String.format(SELECT_USER_BY_EMAIL_AND_PASS, authEntity.getUsername(), authEntity.getPassword());
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(resultQuery);
            while (rs.next()) {
                responseEntity.setUsername(rs.getString(3));
                responseEntity.setPassword(rs.getString(6));
                responseEntity.setPermission("student");
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
