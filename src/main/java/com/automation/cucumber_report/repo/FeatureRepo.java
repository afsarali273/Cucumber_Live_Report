package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepo extends JpaRepository<Feature,Long> {

    //List<Feature> findAllById(Long id);
}
