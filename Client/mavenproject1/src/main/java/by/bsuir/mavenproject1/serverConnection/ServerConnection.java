
package by.bsuir.mavenproject1.serverConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerConnection {
    
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private int PORT = 0;
    
    public JsonNode sendRequest(String command, String JsonString)
    {
        InetAddress host = null;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), 3345);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeUTF(command);
            oos.writeObject(JsonString);
            ois = new ObjectInputStream(socket.getInputStream());
            String resultJson =  ois.readUTF();
            node = mapper.readTree(resultJson);
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException ex) {
            try {
                node = mapper.readTree("{\"status\":400}");
            } catch (JsonProcessingException ex1) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return node;
        }
        return node;
    }
    
    
    
    public String sendRequestGetSting(String command, String JsonString)
    {
        InetAddress host = null;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        String resultJson = null;
        try {
           
            host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), 3345);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeUTF(command);
            oos.writeObject(JsonString);
            ois = new ObjectInputStream(socket.getInputStream());
            resultJson =  ois.readUTF();
            oos.close();
            ois.close();
            socket.close();
            return resultJson;
        
        } catch (IOException ex) {
            try {
                node = mapper.readTree("{\"status\":400}");
            } catch (JsonProcessingException ex1) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return resultJson;
        }
    }
}
