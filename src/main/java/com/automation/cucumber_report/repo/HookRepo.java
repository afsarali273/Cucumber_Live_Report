package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HookRepo extends JpaRepository<Hook,Long> {
}
