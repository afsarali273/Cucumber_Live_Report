package com.automation.cucumber_report.services;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.model.Scenario;
import com.automation.cucumber_report.repo.FeatureRepo;
import com.automation.cucumber_report.repo.ScenarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    private FeatureRepo featureRepo;
    @Autowired
    private ScenarioRepo scenarioRepo;

    public List<Feature> getFeatureList(){ return featureRepo.findAllFeature(); }

    public List<Scenario> getScenariosByFeatureName(String featureName){ return scenarioRepo.findScenariosByFeatureName(featureName); }

    public List<Scenario> getScenariosByFeatureId(int id){ return scenarioRepo.findScenariosByFeatureId(id); }

    public List<Scenario> getScenariosByFeatureIdAndScenarioId(int featId,int scenarioId){
        return scenarioRepo.findScenariosByFeatureIdAndScenarioId(featId,scenarioId); }

    public Optional<Feature> getFeatureById(long id){
       return featureRepo.findById(id) ;
    }
    public List<Feature> getFeatureByName(String name){
        return featureRepo.findByName(name);
    }
}
