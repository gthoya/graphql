package com.gthoya.graphql.coffee.fetcher;

import com.gthoya.graphql.coffee.model.Coffee;
import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CoffeeDataFetcher implements DataFetcher<Coffee> {
    private final CoffeeRepository coffeeRepository;

    public CoffeeDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long coffeeId = dataFetchingEnvironment.getArgument("coffeeId");

        System.out.println("coffeeDataFetcher");

        return coffeeRepository.findByCoffeeId(coffeeId);
    }
}
