package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "feature")
public class Feature implements EntityInterface {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String featureName;

    @Column(name = "uri")
    private String featureUri;

    @Column(name = "description")
    private String featureDescription;

    @Column(name = "keyword")
    private String featureKeyWord;

    @Column(name = "line")
    private Integer featureLineNumber;


    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Scenario> scenarios;

    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Tag> tags;

    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Result> results;

    @Column(name = "status")
    private String featureStatus;

    @Column(name = "duration")
    private long duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public static Feature createFeature(String featureDescription,
                                        String featureKeyWord,
                                        Integer featureLineNumber,
                                        Long duration,
                                        String featureName,
                                        String featureStatus,
                                        String uri ){
         return build(featureDescription,featureKeyWord,featureLineNumber,duration,featureName,featureStatus,uri);
    }

    private static Feature build(
            String featureDescription,
            String featureKeyWord,
            Integer featureLineNumber,
            Long duration,
            String featureName,
            String featureStatus,
            String uri){

       return Feature.builder()
                .featureDescription(featureDescription)
                .featureKeyWord(featureKeyWord)
                .featureLineNumber(featureLineNumber)
                .duration(duration)
                .featureName(featureName)
                .featureStatus(featureStatus)
                .featureUri(uri)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}

