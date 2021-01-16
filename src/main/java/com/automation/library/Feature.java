package com.automation.library;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Durationable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.json.support.StatusCounter;
import net.masterthought.cucumber.util.Util;
import org.apache.commons.lang.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Feature implements Reportable, Durationable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feature_id;

    private final String id = null;
    private final String name = null;
    @JsonProperty("uri")
    private final String uri = null;
    private final String description = null;
    private final String keyword = null;
    private final Integer line = null;
    private Element[] elements = new Element[0];
    private final Tag[] tags = new Tag[0];
    private String reportFileName;
    private String qualifier;
    private final List<Element> scenarios = new ArrayList();
    private final StatusCounter elementsCounter = new StatusCounter();
    private final StatusCounter stepsCounter = new StatusCounter();
    private Status featureStatus;
    private long duration;

    public Feature() {
    }

    public String getId() {
        return this.id;
    }

    public String getUri() {
        return this.uri;
    }

    public void addElements(Element[] newElements) {
        Element[] both = (Element[])Arrays.copyOf(this.elements, this.elements.length + newElements.length);
        System.arraycopy(newElements, 0, both, this.elements.length, newElements.length);
        this.elements = both;
    }

    public Element[] getElements() {
        return this.elements;
    }

    public String getReportFileName() {
        return this.reportFileName;
    }

    public String getQualifier() {
        return this.qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public Tag[] getTags() {
        return this.tags;
    }

    public Status getStatus() {
        return this.featureStatus;
    }

    public String getName() {
        return StringUtils.defaultString(this.name);
    }

    public String getKeyword() {
        return StringUtils.defaultString(this.keyword);
    }

    public Integer getLine() {
        return this.line;
    }

    public String getDescription() {
        return StringUtils.defaultString(this.description);
    }

    public int getFeatures() {
        return 1;
    }

    public int getPassedFeatures() {
        return this.getStatus().isPassed() ? 1 : 0;
    }

    public int getFailedFeatures() {
        return this.getStatus().isPassed() ? 0 : 1;
    }

    public int getScenarios() {
        return this.scenarios.size();
    }

    public int getSteps() {
        return this.stepsCounter.size();
    }

    public int getPassedSteps() {
        return this.stepsCounter.getValueFor(Status.PASSED);
    }

    public int getFailedSteps() {
        return this.stepsCounter.getValueFor(Status.FAILED);
    }

    public int getPendingSteps() {
        return this.stepsCounter.getValueFor(Status.PENDING);
    }

    public int getSkippedSteps() {
        return this.stepsCounter.getValueFor(Status.SKIPPED);
    }

    public int getUndefinedSteps() {
        return this.stepsCounter.getValueFor(Status.UNDEFINED);
    }

    public long getDuration() {
        return this.duration;
    }

    public String getFormattedDuration() {
        return Util.formatDuration(this.duration);
    }

    public int getPassedScenarios() {
        return this.elementsCounter.getValueFor(Status.PASSED);
    }

    public int getFailedScenarios() {
        return this.elementsCounter.getValueFor(Status.FAILED);
    }

//    public void setMetaData(int jsonFileNo, Configuration configuration) {
//        net.masterthought.cucumber.json.Element[] var3 = this.elements;
//        int var4 = var3.length;
//
//        for(int var5 = 0; var5 < var4; ++var5) {
//            net.masterthought.cucumber.json.Element element = var3[var5];
//            element.setMetaData(this, configuration);
//            if (element.isScenario()) {
//                this.scenarios.add(element);
//            }
//        }
//
//        this.reportFileName = this.calculateReportFileName(jsonFileNo);
//        this.featureStatus = this.calculateFeatureStatus();
//        this.calculateSteps();
//    }
//
//    private String calculateReportFileName(int jsonFileNo) {
//        String fileName = "report-feature_";
//        if (jsonFileNo > 0) {
//            fileName = fileName + jsonFileNo + "_";
//        }
//
//        fileName = fileName + Util.toValidFileName(this.uri);
//        fileName = fileName + ".html";
//        return fileName;
//    }
//
//    private Status calculateFeatureStatus() {
//        StatusCounter statusCounter = new StatusCounter();
//        net.masterthought.cucumber.json.Element[] var2 = this.elements;
//        int var3 = var2.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            net.masterthought.cucumber.json.Element element = var2[var4];
//            statusCounter.incrementFor(element.getStatus());
//        }
//
//        return statusCounter.getFinalStatus();
//    }
//
//    private void calculateSteps() {
//        net.masterthought.cucumber.json.Element[] var1 = this.elements;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Element element = var1[var3];
//            if (element.isScenario()) {
//                this.elementsCounter.incrementFor(element.getStatus());
//            }
//
//            Step[] var5 = element.getSteps();
//            int var6 = var5.length;
//
//            for(int var7 = 0; var7 < var6; ++var7) {
//                Step step = var5[var7];
//                this.stepsCounter.incrementFor(step.getResult().getStatus());
//                this.duration += step.getDuration();
//            }
//        }
//
//    }
}

