package com.automation.cucumber_report.repo;

import com.automation.cucumber_report.model.Embedding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmbeddingRepo extends JpaRepository<Embedding,Long> {
}
