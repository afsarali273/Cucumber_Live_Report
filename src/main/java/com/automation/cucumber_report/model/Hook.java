package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.List;

@Entity
@SuperBuilder
@Setter
@Getter
@Table(name = "hook")
public class Hook implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_id")
    private Long id;

//    private Result result = null;
//    private final Match match = null;
    //private List<Output> outputs

    @OneToMany(mappedBy = "hook", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<Embedding> embeddings;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
    private Scenario scenario;

    public static Hook createHook(List<Embedding> embeddings){

        return build(embeddings);
    }
    private static Hook build(List<Embedding> embeddings){
        return Hook.builder()
                .embeddings(embeddings)
                .build();
    }

}
