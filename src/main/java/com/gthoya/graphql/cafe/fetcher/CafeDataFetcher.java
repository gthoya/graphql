package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import com.gthoya.graphql.coffee.model.Coffee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CafeDataFetcher implements DataFetcher<Coffee> {
    private final CoffeeRepository coffeeRepository;

    public CafeDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long coffeeId = dataFetchingEnvironment.getArgument("coffeeId");

        return coffeeRepository.findByCoffeeId(coffeeId);
    }
}
