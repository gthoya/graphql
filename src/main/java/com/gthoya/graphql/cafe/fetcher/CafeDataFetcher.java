package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.cafe.dao.CafeRepository;
import com.gthoya.graphql.cafe.model.Cafe;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CafeDataFetcher implements DataFetcher<Cafe> {
    private final CafeRepository cafeRepository;

    public CafeDataFetcher(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public Cafe get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long cafeId = dataFetchingEnvironment.getArgument("cafeId");

        return cafeRepository.findByCafeId(cafeId);
    }
}
