package com.gthoya.graphql.coffee.service;

import com.gthoya.graphql.common.service.ExecuteProvider;
import com.gthoya.graphql.common.service.ExecuteService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("coffeeService")
public class CoffeeService implements ExecuteService {
    private final ExecuteProvider coffeeProvider;

    public CoffeeService(@Qualifier("coffeeProvider") ExecuteProvider coffeeProvider) {
        this.coffeeProvider = coffeeProvider;
    }

    @Override
    public ExecutionResult execute(String query) {
        return coffeeProvider.execute(query);
    }
}
