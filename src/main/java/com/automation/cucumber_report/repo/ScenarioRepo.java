package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepo extends JpaRepository<Scenario,Long> {
}
