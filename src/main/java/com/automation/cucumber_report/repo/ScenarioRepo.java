package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Feature;
import com.automation.cucumber_report.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScenarioRepo extends JpaRepository<Scenario,Long> {



    @Query(value = "SELECT * FROM scenario s\n" +
            "left join feature f on s.feature_entity_id = f.id\n" +
            "Where f.id =?1",nativeQuery = true)
    List<Scenario> findScenariosByFeatureId(int id);

    @Query(value = "SELECT * FROM scenario s\n" +
            "inner join feature f on s.feature_entity_id = f.id\n" +
            "Where f.id =?1 and s.id=?2",nativeQuery = true)
    List<Scenario> findScenariosByFeatureIdAndScenarioId(int featureId,int scenarioId);

    @Query(value = "SELECT * FROM scenario s\n" +
            "inner join feature f on s.feature_entity_id = f.id\n" +
            "Where f.name =?1",nativeQuery = true)
    List<Scenario> findScenariosByFeatureName(String name);
}
