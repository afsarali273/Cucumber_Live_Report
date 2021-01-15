package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@SuperBuilder
@Setter
@Getter
@Table(name = "datatable_rows")
public class Row implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "rows_id")
    private Long id;

    @OneToMany(mappedBy = "row", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cell> cells;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "step_entity_id",nullable = true, updatable = true, insertable = true)
    private Steps steps;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feature_entity_id",nullable = true, updatable = true, insertable = true)
    private Feature feature;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public static Row createRow(Steps steps,Scenario scenario,Feature feature){
       return Row.builder().feature(feature).scenario(scenario).steps(steps).build();
    }
}
