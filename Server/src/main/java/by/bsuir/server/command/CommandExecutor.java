package by.bsuir.server.command;

import by.bsuir.server.command.impl.*;

public class CommandExecutor {

    public static String executeCommand(String command, String jsonString) {
        if (command.equals("login")) {
            return new LoginCommand(jsonString).execute();
        } else if (command.equals("new-user")) {
            return new NewUserCommand(jsonString).execute();
        } else if (command.equals("user-by-id")) {
            return new GetUserByIDCommand(jsonString).execute();
        } else if (command.equals("update-user")) {
            return new UpdateUserCommand(jsonString).execute();
        } else if (command.equals("delete-user")) {
            return new DeleteUserCommand(jsonString).execute();
        } else if (command.equals("save-minus-or-plus")) {
            return new SaveMinusOrPlusCommand(jsonString).execute();
        } else if (command.equals("load-minus-plus")) {
            return new LoadMinusOrPlusCommand(jsonString).execute();
        } else if (command.equals("delete-minus-plus")) {
            return new DeleteMinusOrPlusCommand(jsonString).execute();
        } else if (command.equals("get-user-by-email")) {
            return new GetUserByEmailCommand(jsonString).execute();
        }
        return "{\"status\":400}";
    }
}
