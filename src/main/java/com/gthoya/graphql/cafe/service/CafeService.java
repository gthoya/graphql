package com.gthoya.graphql.cafe.service;

import com.gthoya.graphql.cafe.provider.CafeDetails;
import graphql.ExecutionResult;
import org.springframework.stereotype.Service;

@Service
public class CafeService {
    private final CafeDetails cafeDetails;

    public CafeService(CafeDetails cafeDetails) {
        this.cafeDetails = cafeDetails;
    }

    public ExecutionResult execute(String query) {
        return cafeDetails.execute(query);
    }
}
