package com.gthoya.graphql.coffee.dao;

import com.gthoya.graphql.coffee.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, String> {
    Coffee findByCid(String cid);
}
