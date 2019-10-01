package com.gthoya.graphql.cafe.service;

import com.gthoya.graphql.common.service.ExecuteProvider;
import com.gthoya.graphql.common.service.ExecuteService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cafeService")
public class CafeService implements ExecuteService {
    private final ExecuteProvider cafeProvider;

    public CafeService(@Qualifier("cafeProvider") ExecuteProvider cafeProvider) {
        this.cafeProvider = cafeProvider;
    }

    @Override
    public ExecutionResult execute(String query) {
        return cafeProvider.execute(query);
    }
}
