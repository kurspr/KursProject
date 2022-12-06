package by.bsuir.server.command;

import by.bsuir.server.dao.AdminDAO;
import by.bsuir.server.dao.BugalterDAO;
import by.bsuir.server.entity.AuthEntity;

public class LoginCommand {

    public static boolean login(AuthEntity authEntity)
    {
        boolean result = false;
        AuthEntity responseEntity = new AuthEntity();
        if(authEntity.getPermission().equals("admin"))
        {
           responseEntity =  AdminDAO.checkAdmin(authEntity);
           result = responseEntity.equals(authEntity);
        }else if(authEntity.getPermission().equals("bugalter"))
        {
            responseEntity = BugalterDAO.checkBugalter(authEntity);
            result = responseEntity.equals(authEntity);
        }
        return result;
    }

}
