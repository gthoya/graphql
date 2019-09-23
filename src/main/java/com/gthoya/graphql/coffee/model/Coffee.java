package com.gthoya.graphql.coffee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String name;

    public Coffee() {

    }

    public Coffee(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
