package com.automation.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.masterthought.cucumber.json.Match;
import net.masterthought.cucumber.json.Output;
import net.masterthought.cucumber.json.Result;
import net.masterthought.cucumber.json.deserializers.OutputsDeserializer;
import net.masterthought.cucumber.json.support.Argument;
import net.masterthought.cucumber.json.support.Resultsable;
import net.masterthought.cucumber.json.support.Status;

public class Step implements Resultsable {
    private String name = null;
    private final String keyword = null;
    private Integer line = null;
    private final net.masterthought.cucumber.json.Result result = new net.masterthought.cucumber.json.Result();
    private final net.masterthought.cucumber.json.Row[] rows = new net.masterthought.cucumber.json.Row[0];
    @JsonProperty("arguments")
    private final Argument[] arguments = new Argument[0];
    private final net.masterthought.cucumber.json.Match match = null;
    private final net.masterthought.cucumber.json.Embedding[] embeddings = new net.masterthought.cucumber.json.Embedding[0];
    @JsonDeserialize(
            using = OutputsDeserializer.class
    )
    @JsonProperty("output")
    private final net.masterthought.cucumber.json.Output[] outputs = new net.masterthought.cucumber.json.Output[0];
    @JsonProperty("doc_string")
    private final net.masterthought.cucumber.json.DocString docString = null;
    private final net.masterthought.cucumber.json.Hook[] before = new net.masterthought.cucumber.json.Hook[0];
    private final net.masterthought.cucumber.json.Hook[] after = new net.masterthought.cucumber.json.Hook[0];
    private Status beforeStatus;
    private Status afterStatus;

    public Step() {
    }

    @Override
    public Result getResult() {
        return null;
    }

    @Override
    public Match getMatch() {
        return null;
    }

    @Override
    public Output[] getOutputs() {
        return new Output[0];
    }

//    public Row[] getRows() {
//        int argumentsSize = ArrayUtils.getLength(this.arguments);
//        if (argumentsSize == 1) {
//            return this.arguments[0].getRows();
//        } else if (argumentsSize > 1) {
//            throw new UnsupportedOperationException("'arguments' length should be equal to 1");
//        } else {
//            return this.rows;
//        }
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getKeyword() {
//        return this.keyword.trim();
//    }
//
//    public Integer getLine() {
//        return this.line;
//    }
//
//    public Output[] getOutputs() {
//        return this.outputs;
//    }
//
//    public Match getMatch() {
//        return this.match;
//    }
//
//    public Embedding[] getEmbeddings() {
//        return this.embeddings;
//    }
//
//    public Result getResult() {
//        return this.result;
//    }
//
//    public long getDuration() {
//        return this.result.getDuration();
//    }
//
//    public DocString getDocString() {
//        return this.docString;
//    }
//
//    public net.masterthought.cucumber.json.Hook[] getBefore() {
//        return this.before;
//    }
//
//    public Hook[] getAfter() {
//        return this.after;
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
//    public void setMetaData() {
//        this.beforeStatus = (new StatusCounter(this.before)).getFinalStatus();
//        this.afterStatus = (new StatusCounter(this.after)).getFinalStatus();
//    }
}

