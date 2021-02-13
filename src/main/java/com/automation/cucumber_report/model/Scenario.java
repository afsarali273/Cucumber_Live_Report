package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "scenario")
public class Scenario implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String scenarioName;

    @Column(name = "uniqueId")
    private String uniqueId;

    @Column(name = "descriptions")
    private String scenarioDescriptions;

    @Column(name = "type")
    private String type;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "line")
    private Integer line;

    @Column(name = "start_timestamp")
    private LocalDateTime startTime;

    @Column(name = "scenario_status")
    private String scenarioStatus;

    @Column(name = "before_status")
    private String beforeStatus;

    @Column(name = "after_status")
    private String afterStatus;

    @Column(name = "steps_status")
    private String stepsStatus;

    @Column(name = "duration")
    private String duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feature_entity_id",nullable = true, updatable = true, insertable = true)
    @JsonIgnore
    private Feature feature;

    //    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Hook> hooks;

    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Steps> steps;
//
//    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Tag> tags;

//    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Result> results;

//    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Embedding> embeddings;

//    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Row> rows;

    public static Scenario createScenario(Feature feature,
                                          String beforeStatus,
                                          String afterStatus,
                                          String duration,
                                          String keyword,
                                          Integer scenarioLine,
                                          String scenarioDesc,
                                          String scenarioName,
                                          String status,
                                          LocalDateTime startTime,
                                          String stepsStatus,
                                          String type,
                                          String uniqueId) {

        return build(feature,beforeStatus,
                afterStatus,
                duration,
                keyword,
                scenarioLine,
                scenarioDesc,
                scenarioName,
                status,
                startTime,
                stepsStatus,
                type,
                uniqueId);
    }

    private static Scenario build(
            Feature feature,
            String beforeStatus,
            String afterStatus,
            String duration,
            String keyword,
            Integer scenarioLine,
            String scenarioDesc,
            String scenarioName,
            String status,
            LocalDateTime startTime,
            String stepsStatus,
            String type,
            String uniqueId
    ) {

        return Scenario.builder()
                .feature(feature)
                .afterStatus(afterStatus)
                .beforeStatus(beforeStatus)
                .createdAt(Timestamp.from(Instant.now()))
                .duration(duration)
                .keyword(keyword)
                .line(scenarioLine)
                .scenarioDescriptions(scenarioDesc)
                .scenarioName(scenarioName)
                .scenarioStatus(status)
                .startTime(startTime)
                .stepsStatus(stepsStatus)
                .type(type)
                .uniqueId(uniqueId)
                .build();
    }

}
