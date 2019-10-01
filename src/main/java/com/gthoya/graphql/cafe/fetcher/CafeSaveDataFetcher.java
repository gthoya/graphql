package com.gthoya.graphql.cafe.fetcher;

import com.gthoya.graphql.cafe.dao.CafeRepository;
import com.gthoya.graphql.cafe.model.Cafe;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CafeSaveDataFetcher implements DataFetcher<Cafe> {
    private final CafeRepository cafeRepository;

    public CafeSaveDataFetcher(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    @Transactional
    public Cafe get(DataFetchingEnvironment dataFetchingEnvironment) {
        Cafe cafe = new Cafe();
        if (dataFetchingEnvironment.getArgument("cafeId") != null) {
            cafe.setCafeId(dataFetchingEnvironment.getArgument("cafeId"));
        }

        cafe.setCafeName(dataFetchingEnvironment.getArgument("cafeName"));

        return cafeRepository.save(cafe);
    }
}
