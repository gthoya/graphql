package com.gthoya.graphql.menu.service;

import com.gthoya.graphql.menu.provider.MenuDetails;
import graphql.ExecutionResult;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuDetails menuDetails;

    public MenuService(MenuDetails menuDetails) {
        this.menuDetails = menuDetails;
    }

    public ExecutionResult execute(String query) {
        return menuDetails.execute(query);
    }
}
