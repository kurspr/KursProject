package by.bsuir.server.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Command {

    protected ObjectMapper mapper = new ObjectMapper();;

    protected String jsonString;

    public Command(String jsonString) {
        this.jsonString = jsonString;
    }

    public abstract String execute();
}
