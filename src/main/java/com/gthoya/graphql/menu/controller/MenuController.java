package com.gthoya.graphql.menu.controller;

import com.gthoya.graphql.common.service.ExecuteService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/menus")
@RestController
public class MenuController {
    private final ExecuteService menuService;

    public MenuController(@Qualifier("menuService") ExecuteService menuService) {
        this.menuService = menuService;
    }


    @PostMapping
    public ResponseEntity<Object> menuByQuery(@RequestBody String query) {
        ExecutionResult executionResult = menuService.execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
