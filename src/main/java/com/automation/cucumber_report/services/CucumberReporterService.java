package com.automation.cucumber_report.services;

import com.automation.cucumber_report.model.*;
import com.automation.cucumber_report.repo.*;
import com.automation.cucumber_report.utils.Utils;
import net.masterthought.cucumber.*;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.Row;
import net.masterthought.cucumber.json.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CucumberReporterService {

    @Autowired
    public FeatureRepo featureRepo;

    @Autowired
    public  ScenarioRepo scenarioRepo;

    @Autowired
    public  StepsRepo stepsRepo;

    @Autowired
    public  TagRepo tagRepo;

    @Autowired
    public  RowRepo rowRepo;

    @Autowired
    public  ResultRepo resultRepo;

    @Autowired
    public  HookRepo hookRepo;

    @Autowired
    public  EmbeddingRepo embeddingRepo;

    @Autowired
    public  CellRepo cellRepo;



    public List<com.automation.cucumber_report.model.Feature> getFeaturesList() throws NoSuchFieldException, IllegalAccessException {

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(System.getProperty("user.dir")+"/src/main/resources/cucumber.json");

        String buildNumber = "1";
        String projectName = "cucumberProject";

        Configuration configuration = new Configuration(new File(System.getProperty("user.dir")+"/src/main/resources/cucumber.json"),projectName);
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Firefox");
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

        Field privateField = ReportBuilder.class.getDeclaredField("reportParser");

        privateField.setAccessible(true);
        ReportParser reportParser = (ReportParser) privateField.get(reportBuilder);
        List<Feature> features = reportParser.parseJsonFiles(jsonFiles);

       List<com.automation.cucumber_report.model.Feature> featureList =  mapToDB(features);

        System.out.println(featureList);
        return featureList;

    }


    public List<com.automation.cucumber_report.model.Feature> mapToDB(List<Feature> features){

        List<com.automation.cucumber_report.model.Feature> featureList = new ArrayList<>();

        features.stream().forEach( feature -> {

            List<Scenario> scenarios = new ArrayList<>();
            //Create Scenario
            Arrays.asList(feature.getElements()).stream().forEach(scenario -> {

                //Steps
              List<Steps> steps = getStepsList(scenario);
               // stepsRepo.saveAll(steps);
                scenarios.add(Scenario.createScenario(Utils.checkIfNullReturnEmpty(scenario.getBeforeStatus() == null ? "":scenario.getBeforeStatus().getRawName()),
                        Utils.checkIfNullReturnEmpty(scenario.getAfterStatus() == null ? "": scenario.getAfterStatus().getRawName()),
                        scenario.getDuration(),
                        getHooks(scenario),
                        Utils.checkIfNullReturnEmpty(scenario.getKeyword()),
                        scenario.getLine(),
                        Utils.checkIfNullReturnEmpty(scenario.getDescription()),
                        Utils.checkIfNullReturnEmpty(scenario.getName()),
                        Utils.checkIfNullReturnEmpty(scenario.getStatus() == null ? "":scenario.getStatus().getRawName()),
                        scenario.getStartTime(),
                        steps,
                        Utils.checkIfNullReturnEmpty(scenario.getStepsStatus() == null ? "":scenario.getStepsStatus().getRawName()),
                        getTags(scenario),
                        Utils.checkIfNullReturnEmpty(scenario.getType())));
            });
            //scenarioRepo.saveAll(scenarios);

            //Create
            featureList.add(com.automation.cucumber_report.model.Feature.createFeature(scenarios,
                    getTags(feature),
                    feature.getDescription(),
                    feature.getKeyword(),
                    feature.getLine(),
                    feature.getDuration(),
                    feature.getName(),
                    feature.getStatus() == null ? "":feature.getStatus().getRawName(),
                    feature.getUri()));
        });
        featureRepo.saveAll(featureList);
    return featureList;
    }


    public List<Steps> getStepsList(Element scenario){
        List<Steps> stepsList = new ArrayList<>();
        Arrays.asList(scenario.getSteps()).stream().forEach( step -> {

            stepsList.add(getStep(step));
        });
        return stepsList;
    }


    public  Steps getStep(Step step){
       Steps steps =  Steps.createStep(Utils.checkIfNullReturnEmpty(step.getKeyword()),
                step.getLine(),
               Utils.checkIfNullReturnEmpty(step.getName()),
                getResult(step),
                getEmbedding(step),
                getRows(step));
       stepsRepo.save(steps);
        return steps;
    }

    public  List<Embedding> getEmbedding(Step step){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(step.getEmbeddings()).stream().forEach(embedding -> {
            embeddingList.add(Embedding.createEmbedding(Utils.checkIfNullReturnEmpty(embedding.getData()),Utils.checkIfNullReturnEmpty(embedding.getFileId()),Utils.checkIfNullReturnEmpty(embedding.getMimeType()),Utils.checkIfNullReturnEmpty(embedding.getName())));
        });
        embeddingRepo.saveAll(embeddingList);
       return embeddingList;
    }

    public List<Embedding> getEmbeddingBefore(Element scenario){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(scenario.getBefore()).stream().forEach(beforeScenario -> {
            Arrays.stream(beforeScenario.getEmbeddings()).forEach(embedding -> {
                embeddingList.add(Embedding.createEmbedding(embedding.getData(),embedding.getFileId(),embedding.getMimeType(),embedding.getName()));
            });
            embeddingRepo.saveAll(embeddingList);
        });
        return embeddingList;
    }

    public List<Embedding> getEmbeddingAfter(Element scenario){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(scenario.getAfter()).stream().forEach(afterScenario -> {
            Arrays.stream(afterScenario.getEmbeddings()).forEach(embedding -> {
                embeddingList.add(Embedding.createEmbedding(Utils.checkIfNullReturnEmpty(embedding.getData()),Utils.checkIfNullReturnEmpty(embedding.getFileId()),Utils.checkIfNullReturnEmpty(embedding.getMimeType()),Utils.checkIfNullReturnEmpty(embedding.getName())));
            });
        });
    embeddingRepo.saveAll(embeddingList);
        return embeddingList;
    }

    public  List<com.automation.cucumber_report.model.Row> getRows(Step step){
        List<com.automation.cucumber_report.model.Row> rowList = new ArrayList<>();
        Arrays.asList(step.getRows()).stream().forEach(row -> {
           List<Cell> cellList =  getCells(row);
            rowList.add(com.automation.cucumber_report.model.Row.createRow(cellList));
        });
        rowRepo.saveAll(rowList);
        return rowList;
    }

    public  List<Cell> getCells(Row row){
        List<Cell> cellList = new ArrayList<>();
        Arrays.asList(row.getCells()).stream().forEach(cell->{
            cellList.add(Cell.createCell(Utils.checkIfNullReturnEmpty(cell)));
        });
        cellRepo.saveAll(cellList);
        return cellList;
    }

    public Result getResult(Step step){
       net.masterthought.cucumber.json.Result result =  step.getResult();
        Result resultStatus = Result.createResult(Utils.checkIfNullReturnEmpty(result.getStatus().toString()),result.getDuration(),Utils.checkIfNullReturnEmpty(result.getErrorMessage()));
        resultRepo.save(resultStatus);
        return resultStatus;
    }


    public List<Tag> getTags(Element scenario){

        List<Tag> tagList = new ArrayList<>();
        Arrays.asList(scenario.getTags()).stream().forEach(tag -> {
            tagList.add(Tag.createTags(Utils.checkIfNullReturnEmpty(tag.getFileName())));
        });
        tagRepo.saveAll(tagList);
        return tagList;
    }

    public List<Tag> getTags(Feature feature){

        List<Tag> tagList = new ArrayList<>();
        Arrays.asList(feature.getTags()).stream().forEach(tag -> {
            tagList.add(Tag.createTags(Utils.checkIfNullReturnEmpty(tag.getFileName())));
        });
        tagRepo.saveAll(tagList);
        return tagList;
    }

    public List<Hook> getHooks(Element scenario){

        List<Hook> hookList = new ArrayList<>();
        Arrays.asList(scenario.getBefore()).stream().forEach(hook -> {

//            hook.getResult();
//            hook.getResult();
//            hook.getEmbeddings();
            hookList.add(Hook.createHook(getEmbeddingAfter(scenario)));
        });
        hookRepo.saveAll(hookList);
        return hookList;
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //System.out.println(getFeaturesList());
    }
}

//
//scenario.getDescription();
//        scenario.getAfter();
//        scenario.getAfterStatus();
//        scenario.getBefore();
//        scenario.getBeforeStatus();
//        scenario.getDuration();
//        scenario.getFormattedDuration();
//        scenario.getId();
//        scenario.getKeyword();
//        scenario.getLine();
//        scenario.getStartTime();
//        scenario.getSteps();
//        scenario.getStepsStatus();
//        scenario.getName();
//        scenario.getTags();
//        scenario.getType();
//        scenario.isBackground();
//        scenario.isBackground();

//=========

//  feature.getElements();
//          feature.getDescription();
//          feature.getDuration();
//          feature.getFailedFeatures();
//          feature.getFailedScenarios();
//          feature.getFailedSteps();
//          feature.getFormattedDuration();
//          feature.getId();
//          feature.getFeatures();
//          feature.getKeyword();
//          feature.getName();
//          feature.getLine();
//          feature.getUri();
//          feature.getPassedFeatures();
//          feature.getPassedScenarios();
//          feature.getPassedSteps();
//          feature.getPendingSteps();
//          feature.getSkippedSteps();

//==========
//            embedding.getData();
//            embedding.getFileId();
//            embedding.getDecodedData();
//            embedding.getFileName();
//            embedding.getMimeType();
//            embedding.getFileId();
//            embedding.getName();

//===========
//            step.getAfterStatus();
//            step.getAfter();
//            step.getBefore();
//            step.getBeforeStatus();
//            step.getDocString();
//            step.getAfterStatus();
//            step.getDuration();
//            step.getEmbeddings();
//            step.getKeyword();
//            step.getLine();
//            step.getMatch();
//            step.getName();
//            step.getOutputs();
//            step.getResult();
//            step.getRows();
//            step.getOutputs();