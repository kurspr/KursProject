package by.bsuir.server.thread;

import by.bsuir.server.command.CommandExecutor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    public ThreadClientHandler(Socket client) {
        ThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(clientDialog.getInputStream());
            oos = new ObjectOutputStream(clientDialog.getOutputStream());
            System.out.println("DataInputStream created");
            System.out.println("DataOutputStream  created");
            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");
                String command = ois.readUTF();
                System.out.println(command);
                if (command.equals("exit")) {
                    break;
                }
                String jsonResponseString = null;
                try {
                    String jsonNodeString = ois.readObject().toString();
                    jsonResponseString = CommandExecutor.executeCommand(command, jsonNodeString);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                oos.writeUTF(jsonResponseString);
                oos.flush();
                clientDialog.close();
            }
            ois.close();
            oos.close();
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
