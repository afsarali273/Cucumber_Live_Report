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

    @Column(name = "type")
    private String type;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feature_entity_id",nullable = true, updatable = true, insertable = true)
    private Feature feature;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "step_entity_id",nullable = true, updatable = true, insertable = true)
    private Steps steps;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hook_entity_id",nullable = true, updatable = true, insertable = true)
    private Hook hook;


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

    public static Result build(Feature feature,Scenario scenario,String status, Long duration, String errorMessage) {
        return Result.builder()
                .feature(feature)
                .scenario(scenario)
                .type("SCENARIO")
                .status(status)
                .duration(duration)
                .errorMessage(errorMessage)
                .build();
    }

    public static Result build(Feature feature,Scenario scenario,Steps steps,String status, Long duration, String errorMessage) {
        return Result.builder()
                .feature(feature)
                .scenario(scenario)
                .status(status)
                .steps(steps)
                .type("STEPS")
                .duration(duration)
                .errorMessage(errorMessage)
                .build();
    }

    public static Result build(Scenario scenario,Hook hook,String type,String status, Long duration, String errorMessage) {
        return Result.builder()
                .scenario(scenario)
                .status(status)
                .hook(hook)
                .type(type)
                .duration(duration)
                .errorMessage(errorMessage)
                .build();
    }

    public static Result build(Steps steps,Hook hook,String type,String status, Long duration, String errorMessage) {
        return Result.builder()
                .steps(steps)
                .status(status)
                .hook(hook)
                .type(type)
                .duration(duration)
                .errorMessage(errorMessage)
                .build();
    }

    private static Result build(Feature feature,String status, Long duration, String errorMessage) {
        return Result.builder()
                .feature(feature)
                .status(status)
                .duration(duration)
                .type("FEATURE")
                .errorMessage(errorMessage)
                .build();
    }
}
