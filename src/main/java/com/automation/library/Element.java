package com.automation.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.masterthought.cucumber.json.support.Durationable;
import net.masterthought.cucumber.json.support.Status;


import java.time.LocalDateTime;

public class Element implements Durationable {
    private final String id = null;
    private final String name = null;
    private final String type = null;
    private final String description = null;
    private final String keyword = null;
    private final Integer line = null;
    @JsonProperty("start_timestamp")
    private final LocalDateTime startTime = null;
    private final Step[] steps = new Step[0];
    private final Hook[] before = new Hook[0];
    private final Hook[] after = new Hook[0];
    private final Tag[] tags = new Tag[0];
    private static final String SCENARIO_TYPE = "scenario";
    private static final String BACKGROUND_TYPE = "background";
    private Status elementStatus;
    private Status beforeStatus;
    private Status afterStatus;
    private Status stepsStatus;
    private Feature feature;
    private long duration;

    public Element() {
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public String getFormattedDuration() {
        return null;
    }

//    public Step[] getSteps() {
//        return this.steps;
//    }
//
//    public Hook[] getBefore() {
//        return this.before;
//    }
//
//    public Hook[] getAfter() {
//        return this.after;
//    }
//
//    public Tag[] getTags() {
//        return this.tags;
//    }
//
//    public Status getStatus() {
//        return this.elementStatus;
//    }
//
//    public Status getBeforeStatus() {
//        return this.beforeStatus;
//    }
//
//    public Status getAfterStatus() {
//        return this.afterStatus;
//    }
//
//    public Status getStepsStatus() {
//        return this.stepsStatus;
//    }
//
//    public String getId() {
//        return this.id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getKeyword() {
//        return this.keyword;
//    }
//
//    public LocalDateTime getStartTime() {
//        return this.startTime;
//    }
//
//    public Integer getLine() {
//        return this.line;
//    }
//
//    public String getType() {
//        return this.type;
//    }
//
//    public String getDescription() {
//        return StringUtils.defaultString(this.description);
//    }
//
//    public boolean isScenario() {
//        return "scenario".equalsIgnoreCase(this.type);
//    }
//
//    public boolean isBackground() {
//        return "background".equalsIgnoreCase(this.type);
//    }
//
//    public Feature getFeature() {
//        return this.feature;
//    }
//
//    public long getDuration() {
//        return this.duration;
//    }
//
//    public String getFormattedDuration() {
//        return Util.formatDuration(this.duration);
//    }
//
//    public void setMetaData(Feature feature, Configuration configuration) {
//        this.feature = feature;
//        Step[] var3 = this.steps;
//        int var4 = var3.length;
//
//        for(int var5 = 0; var5 < var4; ++var5) {
//            Step step = var3[var5];
//            step.setMetaData();
//        }
//
//        this.beforeStatus = (new StatusCounter(this.before)).getFinalStatus();
//        this.afterStatus = (new StatusCounter(this.after)).getFinalStatus();
//        this.stepsStatus = (new StatusCounter(this.steps, configuration.getNotFailingStatuses())).getFinalStatus();
//        this.elementStatus = this.calculateElementStatus();
//        this.calculateDuration();
//    }
//
//    private Status calculateElementStatus() {
//        StatusCounter statusCounter = new StatusCounter();
//        statusCounter.incrementFor(this.stepsStatus);
//        statusCounter.incrementFor(this.beforeStatus);
//        statusCounter.incrementFor(this.afterStatus);
//        return statusCounter.getFinalStatus();
//    }
//
//    private void calculateDuration() {
//        Step[] var1 = this.steps;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Step step = var1[var3];
//            this.duration += step.getResult().getDuration();
//        }

    }
