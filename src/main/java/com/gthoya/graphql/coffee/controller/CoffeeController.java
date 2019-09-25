package com.gthoya.graphql.coffee.controller;

import com.gthoya.graphql.coffee.service.CoffeeService;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coffees")
@RestController
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity<Object> coffeeByQuery(@RequestBody String query) {
        ExecutionResult executionResult = coffeeService.execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
