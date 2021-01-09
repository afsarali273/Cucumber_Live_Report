package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Steps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepsRepo extends JpaRepository<Steps,Long> {
}
