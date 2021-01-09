package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Entity
@SuperBuilder
@Table(name = "steps")
public class Steps implements EntityInterface {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "steps_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "line")
    private Integer line;

    @Column(name = "result")
    private Result result;

    @OneToMany(mappedBy = "steps", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Row> rowId;

    @OneToMany(mappedBy = "steps", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Embedding> embeddings;

//    private final Argument[] arguments = new Argument[0];
//    private final Match match = null;
//    private final Output[] outputs = new Output[0];
//    private final DocString docString = null;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scenario_entity_id")
    private Scenario scenario;

    public static Steps createStep(String keyword,
                                   Integer lineNumber,
                                   String stepName,
                                   Result result,
                                   List<Embedding> embeddings,
                                   List<Row> rows) {

        return build(keyword, lineNumber, stepName, result, embeddings, rows);
    }

    private static Steps build(String keyword,
                               Integer lineNumber,
                               String stepName,
                               Result result,
                               List<Embedding> embeddings,
                               List<Row> rows) {
        return Steps.builder()
                .keyword(keyword)
                .line(lineNumber)
                .name(stepName)
                .result(result)
                .embeddings(embeddings)
                .rowId(rows)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

}
