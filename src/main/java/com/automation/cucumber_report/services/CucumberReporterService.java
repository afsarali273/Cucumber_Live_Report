package com.automation.cucumber_report.services;

import com.automation.cucumber_report.model.*;
import com.automation.cucumber_report.repo.*;
import com.automation.cucumber_report.utils.Utils;
import net.masterthought.cucumber.*;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

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



    public Set<com.automation.cucumber_report.model.Feature> getFeaturesList() throws NoSuchFieldException, IllegalAccessException {

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(System.getProperty("user.dir")+"/src/main/resources/cucumber.json");
       // jsonFiles.add("/Users/a866716/Downloads/test_results 6/cucumber-reports/cucumber-6.json");

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

       Set<com.automation.cucumber_report.model.Feature> featureList =  mapToDB(features);

        System.out.println(featureList);
        return featureList;

    }


    public Set<com.automation.cucumber_report.model.Feature> mapToDB(List<Feature> features){

        Set<com.automation.cucumber_report.model.Feature> featureList = new HashSet<>();

        features.stream().forEach( feature -> {

            //Create
            com.automation.cucumber_report.model.Feature feature1 = com.automation.cucumber_report.model.Feature.createFeature(
                    feature.getDescription(),
                    feature.getKeyword(),
                    feature.getLine(),
                    feature.getDuration(),
                    feature.getName(),
                    feature.getStatus() == null ? "":feature.getStatus().getLabel(),
                    feature.getUri());

            //Create Scenario
            Arrays.asList(feature.getElements()).stream().forEach(scenario -> {

                //Steps
                List<Steps> steps = new ArrayList<>();

             Scenario scenario1=   Scenario.createScenario(feature1,Utils.checkIfNullReturnEmpty(scenario.getBeforeStatus() == null ? "":scenario.getBeforeStatus().getRawName()),
                        Utils.checkIfNullReturnEmpty(scenario.getAfterStatus() == null ? "": scenario.getAfterStatus().getRawName()),
                        scenario.getDuration(),
                        Utils.checkIfNullReturnEmpty(scenario.getKeyword()),
                        scenario.getLine(),
                        Utils.checkIfNullReturnEmpty(scenario.getDescription()),
                        Utils.checkIfNullReturnEmpty(scenario.getName()),
                        Utils.checkIfNullReturnEmpty(scenario.getStatus() == null ? "":scenario.getStatus().getRawName()),
                        scenario.getStartTime(),
                        Utils.checkIfNullReturnEmpty(scenario.getStepsStatus() == null ? "":scenario.getStepsStatus().getRawName()),
                        Utils.checkIfNullReturnEmpty(scenario.getType()));

               getTags(scenario1,scenario);
               getHooks(scenario1,scenario);
               getStepsList(scenario1,scenario);


            });
            //scenarioRepo.saveAll(scenarios);
            getTags(feature1,feature);
            featureList.add(feature1);
        });
        featureRepo.saveAll(featureList);
    return featureList;
    }


    public List<Steps> getStepsList(Scenario scenarios,Element scenario){
        List<Steps> stepsList = new ArrayList<>();
        Arrays.asList(scenario.getSteps()).stream().forEach( step -> {

            stepsList.add(getStep(scenarios,step));
        });
        return stepsList;
    }


    public  Steps getStep(Scenario scenario,Step step){
       Steps steps =  Steps.createStep(scenario,Utils.checkIfNullReturnEmpty(step.getKeyword()),
                step.getLine(),
               Utils.checkIfNullReturnEmpty(step.getName()),
                getResult(step),
                null,
                null);
        getRows(steps,step);
      // stepsRepo.save(steps);
        getEmbedding(steps,step);
        return steps;
    }

    public  List<Embedding> getEmbedding(Steps steps,Step step){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(step.getEmbeddings()).stream().forEach(embedding -> {
            Embedding.createEmbedding(steps,Utils.checkIfNullReturnEmpty(embedding.getData()),Utils.checkIfNullReturnEmpty(embedding.getFileId()),Utils.checkIfNullReturnEmpty(embedding.getMimeType()),Utils.checkIfNullReturnEmpty(embedding.getName()));
        });
        //embeddingRepo.saveAll(embeddingList);
       return embeddingList;
    }

    public List<Embedding> getEmbeddingBefore(Scenario scenario1,Element scenario,Hook hook){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(scenario.getBefore()).stream().forEach(beforeScenario -> {
            Arrays.stream(beforeScenario.getEmbeddings()).forEach(embedding -> {
               Embedding.createEmbedding(hook,scenario1,embedding.getData(),embedding.getFileId(),embedding.getMimeType(),embedding.getName());

            });
            //embeddingRepo.saveAll(embeddingList);
        });
        return embeddingList;
    }

    public List<Embedding> getEmbeddingAfter(Scenario scenario1,Hook hook,Element scenario){
        List<Embedding> embeddingList = new ArrayList<>();
        Arrays.asList(scenario.getAfter()).stream().forEach(afterScenario -> {
            Arrays.stream(afterScenario.getEmbeddings()).forEach(embedding -> {
                Embedding.createEmbedding(hook,scenario1,Utils.checkIfNullReturnEmpty(embedding.getData()),Utils.checkIfNullReturnEmpty(embedding.getFileId()),Utils.checkIfNullReturnEmpty(embedding.getMimeType()),Utils.checkIfNullReturnEmpty(embedding.getName()));
            });
        });
    embeddingRepo.saveAll(embeddingList);
        return embeddingList;
    }

    public  List<com.automation.cucumber_report.model.Row> getRows(Steps steps,Step step){
        List<com.automation.cucumber_report.model.Row> rowList = new ArrayList<>();
        Arrays.asList(step.getRows()).stream().forEach(row -> {
          Row row1 = com.automation.cucumber_report.model.Row.createRow(steps,null);
          getCells(row1,row);
        });
        //rowRepo.saveAll(rowList);
        return rowList;
    }

    public  List<Cell> getCells(Row rows, net.masterthought.cucumber.json.Row row){
        List<Cell> cellList = new ArrayList<>();
        Arrays.asList(row.getCells()).stream().forEach(cells->{

            cellList.add(Cell.createCell(rows,Utils.checkIfNullReturnEmpty(cells)));
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


    public List<Tag> getTags(Scenario scenario1, Element scenario){

        List<Tag> tagList = new ArrayList<>();
        Arrays.asList(scenario.getTags()).stream().forEach(tag -> {
            tagList.add(Tag.createTags(scenario1,"Scenario",Utils.checkIfNullReturnEmpty(tag.getName())));
        });
        tagRepo.saveAll(tagList);
        return tagList;
    }

    public List<Tag> getTags(com.automation.cucumber_report.model.Feature feature1,Feature feature){

        List<Tag> tagList = new ArrayList<>();
        Arrays.asList(feature.getTags()).stream().forEach(tag -> {
            tagList.add(Tag.createTags(feature1,"Feature",Utils.checkIfNullReturnEmpty(tag.getName())));
        });
        tagRepo.saveAll(tagList);
        return tagList;
    }

    public List<Hook> getHooks(Scenario scenarios,Element scenario){

        List<Hook> hookList = new ArrayList<>();
        Arrays.asList(scenario.getBefore()).stream().forEach(hook -> {

            Hook.createHook(scenarios,"BeforeScenario",Utils.checkIfNullReturnEmpty(hook.getResult().getStatus().getRawName()));

          //  getEmbeddingBefore(scenarios,scenario,hook1);
//            hook.getEmbeddings();
           // hookList.add(Hook.createHook(getEmbeddingAfter(scenario)));
        });

        Arrays.asList(scenario.getAfter()).stream().forEach(hook -> {
            Hook.createHook(scenarios,"AfterScenario",Utils.checkIfNullReturnEmpty(hook.getResult().getStatus().getRawName()));

        });


       // hookRepo.saveAll(hookList);
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