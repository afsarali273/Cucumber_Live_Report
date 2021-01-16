package com.automation.library;
import net.masterthought.cucumber.util.Util;

public class Tag {
    private final String name = null;

    public Tag() {
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return generateFileName(this.name);
    }

    public static String generateFileName(String tagName) {
        return String.format("report-tag_%s.html", Util.toValidFileName(tagName));
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object tag) {
        return ((Tag)tag).name.equals(this.name);
    }
}

