package com.gthoya.graphql.menu.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
@NoArgsConstructor
@IdClass(MenuId.class)
public class Menu {
    @Id
    private Long cafeId;

    @Id
    private Long coffeeId;
}

class MenuId implements Serializable {
    private Long cafeId;
    private Long coffeeId;
}
