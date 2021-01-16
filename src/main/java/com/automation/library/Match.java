package com.automation.library;
import net.masterthought.cucumber.json.support.Argument;

public class Match {
    private final String location = null;
    private final Argument[] arguments = new Argument[0];

    public Match() {
    }

    public String getLocation() {
        return this.location;
    }

    public Argument[] getArguments() {
        return this.arguments;
    }
}

