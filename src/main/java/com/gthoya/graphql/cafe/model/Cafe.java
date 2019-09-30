package com.gthoya.graphql.cafe.model;

import com.gthoya.graphql.menu.model.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeId", nullable = false, insertable = false, updatable = false)
    private List<Menu> menus;
}
