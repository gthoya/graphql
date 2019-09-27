package com.gthoya.graphql.menu.fetcher;

import com.gthoya.graphql.menu.dao.MenuRepository;
import com.gthoya.graphql.menu.model.Menu;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MenuRemoveDataFetcher implements DataFetcher<Menu> {
    private final MenuRepository menuRepository;

    public MenuRemoveDataFetcher(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    @Transactional
    public Menu get(DataFetchingEnvironment dataFetchingEnvironment) {
        Menu menu = new Menu();

        menu.setCafeId(dataFetchingEnvironment.getArgument("cafeId"));
        menu.setCoffeeId(dataFetchingEnvironment.getArgument("coffeeId"));

        menuRepository.delete(menu);

        return menu;
    }
}
