package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import com.gthoya.graphql.coffee.model.Coffee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CafesDataFetcher implements DataFetcher<List<Coffee>> {
    private final CoffeeRepository coffeeRepository;

    public CafesDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<Coffee> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return coffeeRepository.findAll();
    }
}
