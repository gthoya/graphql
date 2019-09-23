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
    private String cid;
    private String name;

    public Coffee() {

    }

    public Coffee(String cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
