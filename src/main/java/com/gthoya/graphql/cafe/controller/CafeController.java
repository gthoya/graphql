package com.gthoya.graphql.cafe.controller;

import com.gthoya.graphql.common.service.ExecuteService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cafes")
@RestController
public class CafeController {
    private final ExecuteService cafeService;

    public CafeController(@Qualifier("cafeService") ExecuteService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping
    public ResponseEntity<Object> coffeeByQuery(@RequestBody String query) {
        ExecutionResult executionResult = cafeService.execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
