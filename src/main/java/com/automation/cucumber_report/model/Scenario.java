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

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "scenario_id")
    private Long id;

    @Column(name = "name")
    private String scenarioName;

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

    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Hook> hooks;

    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Steps> steps;

    @OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Tag> tags;

    @Column(name = "scenario_status")
    private String scenarioStatus;

    @Column(name = "before_status")
    private String beforeStatus;

    @Column(name = "after_status")
    private String afterStatus;

    @Column(name = "steps_status")
    private String stepsStatus;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feature_entity_id",nullable = true, updatable = true, insertable = true)
    private Feature feature;

    public static Scenario createScenario(Feature feature,String beforeStatus,
                                          String afterStatus,
                                          Long duration,
                                          List<Hook> hooks,
                                          String keyword,
                                          Integer scenarioLine,
                                          String scenarioDesc,
                                          String scenarioName,
                                          String status,
                                          LocalDateTime startTime,
                                          List<Steps> steps,
                                          String stepsStatus,
                                          List<Tag> scenarioLevelTags,
                                          String type) {

        return build(feature,beforeStatus,
                afterStatus,
                duration,
                hooks,
                keyword,
                scenarioLine,
                scenarioDesc,
                scenarioName,
                status,
                startTime,
                steps,
                stepsStatus,
                scenarioLevelTags,
                type);
    }

    private static Scenario build(
            Feature feature,
            String beforeStatus,
            String afterStatus,
            Long duration,
            List<Hook> hooks,
            String keyword,
            Integer scenarioLine,
            String scenarioDesc,
            String scenarioName,
            String status,
            LocalDateTime startTime,
            List<Steps> steps,
            String stepsStatus,
            List<Tag> scenarioLevelTags,
            String type
    ) {

        return Scenario.builder()
                .feature(feature)
                .afterStatus(afterStatus)
                .beforeStatus(beforeStatus)
                .createdAt(Timestamp.from(Instant.now()))
                .duration(duration)
                .hooks(hooks)
                .keyword(keyword)
                .line(scenarioLine)
                .scenarioDescriptions(scenarioDesc)
                .scenarioName(scenarioName)
                .scenarioStatus(status)
                .startTime(startTime)
                .steps(steps)
                .stepsStatus(stepsStatus)
                .tags(scenarioLevelTags)
                .type(type)
                .build();
    }

}
