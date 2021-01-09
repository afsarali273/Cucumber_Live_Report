package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Row;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowRepo extends JpaRepository<Row,Long> {
}
