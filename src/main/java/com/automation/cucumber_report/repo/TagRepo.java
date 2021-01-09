package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag,Long> {
}
