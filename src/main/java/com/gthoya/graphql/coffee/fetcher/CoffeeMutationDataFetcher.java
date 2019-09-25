package com.gthoya.graphql.coffee.fetcher;

import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import com.gthoya.graphql.coffee.model.Coffee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CoffeeMutationDataFetcher implements DataFetcher<Coffee> {
    private final CoffeeRepository coffeeRepository;

    public CoffeeMutationDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    @Transactional
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        Coffee coffee = new Coffee();
        if (dataFetchingEnvironment.getArgument("id") != null) {
            coffee.setCid(dataFetchingEnvironment.getArgument("id"));
        }

        coffee.setName(dataFetchingEnvironment.getArgument("name"));

        return coffeeRepository.save(coffee);
    }
}
