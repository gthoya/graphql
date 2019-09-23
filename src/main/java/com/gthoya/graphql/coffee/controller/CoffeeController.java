package com.gthoya.graphql.coffee.controller;

import com.gthoya.graphql.coffee.service.CoffeeUseCase;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coffees")
@RestController
public class CoffeeController {
    private final CoffeeUseCase coffeeUseCase;

    public CoffeeController(CoffeeUseCase coffeeUseCase) {
        this.coffeeUseCase = coffeeUseCase;
    }

    @GetMapping
    public ResponseEntity<Object> getCoffeeByQuery(@RequestBody String query) {
        ExecutionResult executionResult = coffeeUseCase.execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
