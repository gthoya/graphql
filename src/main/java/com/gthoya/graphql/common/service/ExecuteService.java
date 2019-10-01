package com.gthoya.graphql.common.service;

import graphql.ExecutionResult;

public interface ExecuteService {
    ExecutionResult execute(String query);
}
