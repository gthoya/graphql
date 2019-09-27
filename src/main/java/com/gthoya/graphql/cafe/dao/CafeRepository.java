package com.gthoya.graphql.cafe.dao;

import com.gthoya.graphql.cafe.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    Cafe findByCafeId(Long cafeId);
}
