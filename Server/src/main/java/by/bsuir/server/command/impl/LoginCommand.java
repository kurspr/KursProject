package by.bsuir.server.command.impl;

import by.bsuir.server.dao.factory.DAOFactory;
import by.bsuir.server.entity.AuthEntity;

import java.io.IOException;

public class LoginCommand extends Command {

    public LoginCommand(String jsonString) {
        super(jsonString);
    }

    @Override
    public String execute() {
        try {
            AuthEntity authEntity = mapper.readValue(jsonString, AuthEntity.class);
            if (LoginCommand.login(authEntity)) {
                return "{\"status\":200}";
            } else {
                return "{\"status\":400}";
            }
        } catch (IOException e) {
            return "{\"status\":400}";
        }
    }

    private static boolean login(AuthEntity authEntity) {
        boolean result = false;

        AuthEntity responseEntity = new AuthEntity();
        if (authEntity.getPermission().equals("admin")) {
            responseEntity = DAOFactory.getInstance().getAdminDAO().checkAdmin(authEntity);
            result = responseEntity.equals(authEntity);
        } else if (authEntity.getPermission().equals("bugalter")) {
            responseEntity = DAOFactory.getInstance().getBugalterDAO().checkBugalter(authEntity);
            result = responseEntity.equals(authEntity);
        } else if (authEntity.getPermission().equals("student")) {
            responseEntity = DAOFactory.getInstance().getUserDAO().checkStudent(authEntity);
            result = responseEntity.equals(authEntity);
        }

        return result;
    }

}
