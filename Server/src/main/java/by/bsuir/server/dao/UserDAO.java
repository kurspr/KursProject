package by.bsuir.server.dao;

import by.bsuir.server.entity.AuthEntity;
import by.bsuir.server.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void insertNewUser(User user) throws SQLException;

    List<User> selectAllUsers() throws SQLException;

    User selectUserByID(String idUser) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUserById(String idUser) throws SQLException;

    User selectUserByEmail(String email) throws SQLException;

    AuthEntity checkStudent(AuthEntity authEntity);
}
