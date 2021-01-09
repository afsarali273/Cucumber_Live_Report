package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@SuperBuilder
@Table(name = "datatable_rows")
public class Row implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rows_id")
    private Long id;

    @OneToMany(mappedBy = "row", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cell> cells;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "step_entity_id")
    private Steps steps;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public static Row createRow(List<Cell>cells){
       return Row.builder().cells(cells).build();
    }
}
