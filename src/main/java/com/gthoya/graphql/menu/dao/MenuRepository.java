package com.gthoya.graphql.menu.dao;

import com.gthoya.graphql.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByCafeId(Long cafeId);
}
