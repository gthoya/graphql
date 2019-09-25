package com.gthoya.graphql.coffee.fetcher;

import com.gthoya.graphql.coffee.model.Coffee;
import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoffeesDataFetcher implements DataFetcher<List<Coffee>> {
    private final CoffeeRepository coffeeRepository;

    public CoffeesDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<Coffee> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return coffeeRepository.findAll();
    }
}
