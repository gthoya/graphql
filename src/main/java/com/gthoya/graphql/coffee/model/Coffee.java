package com.gthoya.graphql.coffee.model;

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
public class Coffee {
    @Id
    @GeneratedValue
    private Long coffeeId;

    @Column
    private String coffeeName;
}
