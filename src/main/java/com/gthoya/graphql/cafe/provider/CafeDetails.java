package com.gthoya.graphql.cafe.provider;

import graphql.ExecutionResult;

public interface CafeDetails {
    ExecutionResult execute(String query);
}
