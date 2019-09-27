package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import com.gthoya.graphql.coffee.model.Coffee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CafeSaveDataFetcher implements DataFetcher<Coffee> {
    private final CoffeeRepository coffeeRepository;

    public CafeSaveDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    @Transactional
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        Coffee coffee = new Coffee();
        if (dataFetchingEnvironment.getArgument("coffeeId") != null) {
            coffee.setCoffeeId(dataFetchingEnvironment.getArgument("coffeeId"));
        }

        coffee.setCoffeeName(dataFetchingEnvironment.getArgument("coffeeName"));

        return coffeeRepository.save(coffee);
    }
}
