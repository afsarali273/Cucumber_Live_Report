package com.automation.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.masterthought.cucumber.json.Embedding;
import net.masterthought.cucumber.json.Match;
import net.masterthought.cucumber.json.Output;
import net.masterthought.cucumber.json.Result;
import net.masterthought.cucumber.json.deserializers.OutputsDeserializer;
import net.masterthought.cucumber.json.support.Resultsable;
import org.apache.commons.lang.StringUtils;

public class Hook implements Resultsable {
    private final Result result = null;
    private final Match match = null;
    @JsonDeserialize(
            using = OutputsDeserializer.class
    )
    @JsonProperty("output")
    private final Output[] outputs = new Output[0];
    private final net.masterthought.cucumber.json.Embedding[] embeddings = new net.masterthought.cucumber.json.Embedding[0];

    public Hook() {
    }

    public Result getResult() {
        return this.result;
    }

    public Match getMatch() {
        return this.match;
    }

    public Output[] getOutputs() {
        return this.outputs;
    }

    public Embedding[] getEmbeddings() {
        return this.embeddings;
    }

    public boolean hasContent() {
        if (this.embeddings.length > 0) {
            return true;
        } else {
            return StringUtils.isNotBlank(this.result.getErrorMessage());
        }
    }
}
