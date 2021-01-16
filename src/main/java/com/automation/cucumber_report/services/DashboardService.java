package com.automation.cucumber_report.services;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.repo.FeatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private FeatureRepo featureRepo;

    public List<Feature> getFeatureList(){
        List<Feature> feat = featureRepo.findAll();
        return feat;
    }
}
