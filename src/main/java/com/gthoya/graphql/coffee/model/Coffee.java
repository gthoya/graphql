package com.gthoya.graphql.coffee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Coffee {
    @Id
    @GeneratedValue
    private Long cid;

    @Column
    private String name;

    public Coffee() {

    }

    public Coffee(String name) {
        this.name = name;
    }

    public Coffee(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
