package com.gthoya.graphql.menu.service;

import com.gthoya.graphql.common.service.ExecuteProvider;
import com.gthoya.graphql.common.service.ExecuteService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuService implements ExecuteService {
    private final ExecuteProvider menuProvider;

    public MenuService(@Qualifier("menuProvider") ExecuteProvider menuProvider) {
        this.menuProvider = menuProvider;
    }

    @Override
    public ExecutionResult execute(String query) {
        return menuProvider.execute(query);
    }
}
