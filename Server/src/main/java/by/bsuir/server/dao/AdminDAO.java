package by.bsuir.server.dao;

import by.bsuir.server.entity.AuthEntity;

public interface AdminDAO {

    AuthEntity checkAdmin(AuthEntity authEntity);
}
