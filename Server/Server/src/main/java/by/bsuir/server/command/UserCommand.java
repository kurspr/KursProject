package by.bsuir.server.command;

import by.bsuir.server.dao.UserDAO;
import by.bsuir.server.entity.User;

import java.sql.SQLException;


public class UserCommand {

    public static User getUserByID(String idUser) throws SQLException {
        return UserDAO.selectUserByID(idUser);
    }
    public static void newUser(User user) throws SQLException {
        UserDAO.insertNewUser(user);
    }

    public static void updateUser(User user) throws SQLException {
        UserDAO.updateUser(user);
    }

    public static void deleteUser(String idUser) throws SQLException {
        UserDAO.deleteUserById(idUser);
    }

}
