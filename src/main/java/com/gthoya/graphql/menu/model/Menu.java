package com.gthoya.graphql.menu.model;

import com.gthoya.graphql.cafe.model.Cafe;
import com.gthoya.graphql.coffee.model.Coffee;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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

    @ManyToMany
    @JoinColumn(name = "cafeId", nullable = false, insertable = false, updatable = false)
    private List<Cafe> cafe;

    @ManyToMany
    @JoinColumn(name = "coffeeId", nullable = false, insertable = false, updatable = false)
    private List<Coffee> coffee;
}

class MenuId implements Serializable {
    private Long cafeId;
    private Long coffeeId;
}
