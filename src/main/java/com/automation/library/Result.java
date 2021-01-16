package com.automation.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.masterthought.cucumber.json.support.Durationable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.util.Util;

public class Result implements Durationable {
    private final Status status;
    @JsonProperty("error_message")
    private final String errorMessage;
    private final Long duration;

    public Result() {
        this.status = Status.UNDEFINED;
        this.errorMessage = null;
        this.duration = 0L;
    }

    public Status getStatus() {
        return this.status;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getFormattedDuration() {
        return Util.formatDuration(this.duration);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public final String getErrorMessageTitle() {
        if (this.errorMessage != null) {
            String[] title = this.errorMessage.split("[\\p{Space}]+");
            if (title.length > 0) {
                return title[0];
            }
        }

        return "";
    }
}

