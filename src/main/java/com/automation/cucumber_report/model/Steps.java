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
import java.util.List;


@Entity
@SuperBuilder
@Table(name = "steps")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Steps implements EntityInterface {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "steps_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "line")
    private Integer line;

//    @Column(name = "result")
//    private Result result;

    @OneToMany(mappedBy = "steps", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Row> rowId;

    @OneToMany(mappedBy = "steps", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Embedding> embeddings;

    @OneToMany(mappedBy = "steps", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Result> results;

//    private final Argument[] arguments = new Argument[0];
//    private final Match match = null;
//    private final Output[] outputs = new Output[0];
//    private final DocString docString = null;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
    private Scenario scenario;

    public static Steps createStep(Scenario scenario,String keyword,
                                   Integer lineNumber,
                                   String stepName) {

        return build(scenario,keyword, lineNumber, stepName);
    }

    private static Steps build(Scenario scenario,String keyword,
                               Integer lineNumber,
                               String stepName) {
        return Steps.builder()
                .scenario(scenario)
                .keyword(keyword)
                .line(lineNumber)
                .name(stepName)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

}
