package by.bsuir.server.command;

import by.bsuir.server.entity.AuthEntity;
import by.bsuir.server.entity.MinusOrPlus;
import by.bsuir.server.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CommandExecutor {

    public static String executeCommand(String command, String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        if(command.equals("login"))
        {
            try {
                AuthEntity authEntity = mapper.readValue(jsonString, AuthEntity.class);
                if (LoginCommand.login(authEntity)) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("status", 200);
                    return mapper.writeValueAsString(node);
                } else {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("status", 401);
                    return mapper.writeValueAsString(node);
                }
            } catch (IOException e) {
            }
        }else if(command.equals("new-user"))
        {
            User user = null;
            try {
                user = mapper.readValue(jsonString, User.class);
                UserCommand.newUser(user);
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 200);
                return mapper.writeValueAsString(node);
            } catch (JsonProcessingException | SQLException e) {
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 401);
                try {
                    return mapper.writeValueAsString(node);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (command.equals("user-by-id"))
        {
            User user;
            try {
                JsonNode jsonNode = mapper.readTree(jsonString);
                String idUser = String.valueOf(jsonNode.get("idUser"));
                user =  UserCommand.getUserByID(idUser);
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 200);
                node.put("idUser",user.getId());
                node.put("fio",user.getFio());
                node.put("email",user.getEmail());
                node.put("phone",user.getPhone());
                node.put("address",user.getAddress());
                node.put("password",user.getFpassword());
                return mapper.writeValueAsString(node);
            } catch (JsonProcessingException | SQLException e) {
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 401);
                try {
                    return mapper.writeValueAsString(node);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        }else if (command.equals("update-user"))
        {
            User user;
            try {
                user = mapper.readValue(jsonString, User.class);
                UserCommand.updateUser(user);
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 200);
                return mapper.writeValueAsString(node);
            } catch (JsonProcessingException | SQLException e) {
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 401);
                try {
                    return mapper.writeValueAsString(node);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        } else if(command.equals("delete-user"))
        {
            try {
                JsonNode jsonNode = mapper.readTree(jsonString);
                String idUser = String.valueOf(jsonNode.get("id"));
                UserCommand.deleteUser(idUser);
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 200);
                return mapper.writeValueAsString(node);
            } catch (JsonProcessingException | SQLException e) {
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 401);
                try {
                    return mapper.writeValueAsString(node);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        }else if (command.equals("save-minus-or-plus"))
        {
            try {
                List<MinusOrPlus> minusOrPlusList = Arrays.asList(mapper.readValue(jsonString, MinusOrPlus[].class));
                MinusOrPlusCommand.saveMinusOrPlus(minusOrPlusList);
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 200);
                return mapper.writeValueAsString(node);
            } catch (JsonProcessingException  |SQLException e) {
                ObjectNode node = mapper.createObjectNode();
                node.put("status", 401);
                try {
                    return mapper.writeValueAsString(node);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }
}
