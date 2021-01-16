package com.automation.cucumber_report.controller;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.services.CucumberReporterService;
import com.automation.cucumber_report.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/test")
public class TestRestController {

    @Autowired CucumberReporterService cucumberReporterService;
    @Autowired
    DashboardService dashboardService;

    @GetMapping
    public String check() throws NoSuchFieldException, IllegalAccessException {
        List<Feature> featureList = cucumberReporterService.getFeaturesList();

    //  featureList.stream().forEach( x-> Arrays.stream(x.getElements()).count());
        return "Welcome Afsar";
    }


    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE,path = "/features")
    public List<Feature> getFeatureList(){
        return dashboardService.getFeatureList();
    }
}
