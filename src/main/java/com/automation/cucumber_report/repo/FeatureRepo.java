package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepo extends JpaRepository<Feature,Long> {

    //List<Feature> findAllById(Long id);
@Query(value = "select * from feature where name=?1",nativeQuery = true)
    List<Feature> findByName(String featureName);

    @Query(value = "SELECT * FROM feature \n" +
            "order by name",nativeQuery = true)
    List<Feature> findAllFeature();
}
