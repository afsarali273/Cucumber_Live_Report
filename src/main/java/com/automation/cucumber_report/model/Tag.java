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

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag implements EntityInterface{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "tag_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
//    private Scenario scenario;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "feature_entity_id",nullable = true, updatable = true, insertable = true)
//    private Feature feature;

    public static Tag createTags(Scenario scenario,String type,String tagName){
        return Tag.builder()
                .name(tagName)
                .type(type)
                //.scenario(scenario)
                //.feature(scenario.getFeature())
                .build();
    }

    public static Tag createTags(Feature feature,String type,String tagName){
        return Tag.builder()
                .name(tagName)
                .type(type)
                //.feature(feature)
                .build();
    }

}
