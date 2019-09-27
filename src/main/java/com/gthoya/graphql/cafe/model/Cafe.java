package com.gthoya.graphql.cafe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue
    private Long cafeId;

    @Column
    private String cafeName;
}
