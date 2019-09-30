package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.cafe.dao.CafeRepository;
import com.gthoya.graphql.cafe.model.Cafe;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CafesDataFetcher implements DataFetcher<List<Cafe>> {
    private final CafeRepository cafeRepository;

    public CafesDataFetcher(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public List<Cafe> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return cafeRepository.findAll();
    }
}
