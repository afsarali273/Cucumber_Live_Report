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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "step_entity_id",nullable = true, updatable = true, insertable = true)
    private Steps steps;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public static Row createRow(List<Cell>cells){
       return Row.builder().cells(cells).build();
    }
}
