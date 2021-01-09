package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepo extends JpaRepository<Cell,Long> {
}
