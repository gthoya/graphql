package com.gthoya.graphql.menu.fetcher;

import com.gthoya.graphql.menu.dao.MenuRepository;
import com.gthoya.graphql.menu.model.Menu;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class MenuDataFetcher implements DataFetcher<Menu> {
    private final MenuRepository menuRepository;

    public MenuDataFetcher(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cafeId = dataFetchingEnvironment.getArgument("cafeId");

        return menuRepository.findByCafeId(cafeId);
    }
}
