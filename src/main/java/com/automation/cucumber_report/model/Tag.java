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
@Table(name = "tag")
public class Tag implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "scenario_entity_id")
//    private Scenario scenario;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "feature_entity_id")
//    private Feature feature;

    public static Tag createTags(String tagName){
        return Tag.builder()
                .name(tagName)
                .build();
    }

}
