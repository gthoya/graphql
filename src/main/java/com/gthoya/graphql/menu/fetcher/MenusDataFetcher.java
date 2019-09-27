package com.gthoya.graphql.menu.fetcher;

import com.gthoya.graphql.menu.dao.MenuRepository;
import com.gthoya.graphql.menu.model.Menu;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenusDataFetcher implements DataFetcher<List<Menu>> {
    private final MenuRepository menuRepository;

    public MenusDataFetcher(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cafeId = dataFetchingEnvironment.getArgument("cafeId");

        return menuRepository.findByCafeId(cafeId);
    }
}
