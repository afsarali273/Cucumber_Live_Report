package com.automation.cucumber_report.controller;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.model.Scenario;
import com.automation.cucumber_report.services.CucumberReporterService;
import com.automation.cucumber_report.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "/test")
public class TestRestController {

    @Autowired CucumberReporterService cucumberReporterService;
    @Autowired
    DashboardService dashboardService;

    @GetMapping
    public String check() throws NoSuchFieldException, IllegalAccessException {
        cucumberReporterService.getFeaturesList();
        return "Welcome Afsar"; }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE,path = "/features")
    public List<Feature> getFeatureList(){
        return dashboardService.getFeatureList();
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE,path = "/features/{featureId}/scenarios")
    public List<Scenario> getScenarioList(@PathVariable(name = "featureId") Integer id){
        return dashboardService.getScenariosByFeatureId(id);
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE,path = "/features/{featureId}/scenarios/{scenarioId}")
    public List<Scenario> getScenarioList(@PathVariable(name = "featureId") Integer featureId , @PathVariable(name = "scenarioId") Integer scenarioId){
        return dashboardService.getScenariosByFeatureIdAndScenarioId(featureId,scenarioId);
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE,path = "/features/get-latest")
    public List<Feature> getFirstFeatureOnly(){
        return dashboardService.getFeatureList().subList(0,2);
    }
}
