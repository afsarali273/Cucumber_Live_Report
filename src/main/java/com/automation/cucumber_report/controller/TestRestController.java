package com.automation.cucumber_report.controller;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.repo.FeatureRepo;
import com.automation.cucumber_report.services.CucumberReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/test")
public class TestRestController {

    @Autowired CucumberReporterService cucumberReporterService;


    @GetMapping
    public String check() throws NoSuchFieldException, IllegalAccessException {
        List<Feature> featureList = cucumberReporterService.getFeaturesList();

    //  featureList.stream().forEach( x-> Arrays.stream(x.getElements()).count());
        return "Welcome Afsar";
    }
}
