package com.gthoya.graphql.menu.controller;

import com.gthoya.graphql.menu.service.MenuService;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/menus")
@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @PostMapping
    public ResponseEntity<Object> menuByQuery(@RequestBody String query) {
        ExecutionResult executionResult = menuService.execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
