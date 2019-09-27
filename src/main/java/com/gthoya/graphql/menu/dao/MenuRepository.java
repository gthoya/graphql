package com.gthoya.graphql.menu.dao;

import com.gthoya.graphql.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCafeId(Long cafeId);
}
