package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hook")
public class Hook implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "hook_id")
    private Long id;

    @Column(name = "result")
    private String result;

    @Column(name = "hook_type")
    private String hookType;

//    private Result result = null;
//    private final Match match = null;
    //private List<Output> outputs

//    @OneToMany(mappedBy = "hook", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    List<Embedding> embeddings;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "scenario_entity_id",nullable = true, updatable = true, insertable = true)
//    private Scenario scenario;

//    @OneToMany(mappedBy = "hook", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<Result> results;

    public static Hook createHook(Scenario scenario,String hookType,String result){

        return build(scenario,hookType,result);
    }
    private static Hook build(Scenario scenario,String hookType,String result){
        return Hook.builder()
                //.scenario(scenario)
                .hookType(hookType)
                .result(result)
                .build();
    }

}
