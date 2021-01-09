package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result,Long> {
}
