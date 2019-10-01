package com.gthoya.graphql.common.service;

import graphql.ExecutionResult;

public interface ExecuteProvider {
    ExecutionResult execute(String query);
}
