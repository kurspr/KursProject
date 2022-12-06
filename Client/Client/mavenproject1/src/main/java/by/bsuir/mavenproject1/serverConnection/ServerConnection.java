/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package by.bsuir.mavenproject1.serverConnection;

import by.bsuir.mavenproject1.form.WelcomeForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikif
 */
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
    
    
    public void startConnection()
    {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(WelcomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 3345);
             //write to socket using ObjectOutputStream
        //    oos = new ObjectOutputStream(socket.getOutputStream());
        //    System.out.println("Sending request to Socket Server");
       //     oos.writeUTF("login");
            String JSON = "{'name' : 'mkyong'}";
     //       oos.writeObject(JSON);
            //read the server response message
       //     ois = new ObjectInputStream(socket.getInputStream());
       //     String message = (String) ois.readObject();
          //  System.out.println("Message: " + message);
            //close resources
         
        } catch (IOException ex) {
            Logger.getLogger(WelcomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopConnection()
    {
        try {
            ois.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
