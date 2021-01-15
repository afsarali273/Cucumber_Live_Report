package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@SuperBuilder
@Setter
@Getter
@Table(name = "result")
public class Result implements EntityInterface {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "result_id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public static Result createResult(String status, Long duration, String errorMessage) {
        return build(status, duration, errorMessage);
    }

    private static Result build(String status, Long duration, String errorMessage) {
        return Result.builder()
                .status(status)
                .duration(duration)
                .errorMessage(errorMessage)
                .build();
    }
}
