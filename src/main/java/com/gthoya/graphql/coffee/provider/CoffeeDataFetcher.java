package com.gthoya.graphql.coffee.provider;

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
        String cid = dataFetchingEnvironment.getArgument("id");

        return coffeeRepository.findByCid(cid);
    }
}
