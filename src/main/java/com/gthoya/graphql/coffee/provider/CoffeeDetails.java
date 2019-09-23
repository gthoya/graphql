package com.gthoya.graphql.coffee.provider;

import graphql.ExecutionResult;

public interface CoffeeDetails {
    ExecutionResult execute(String query);
}
