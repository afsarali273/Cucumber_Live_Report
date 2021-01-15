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
@Table(name = "cells")
public class Cell implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cell_id")
    private Long id;

    @Column(name = "column_value")
    private String cells;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "row_entity_id",nullable = true, updatable = true, insertable = true)
    Row row;

    public static Cell createCell(String cellsValue){
        return Cell.builder().cells(cellsValue).build();

    }
}
