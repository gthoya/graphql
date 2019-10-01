package com.gthoya.graphql.cafe.service;

import com.gthoya.graphql.cafe.fetcher.CafeDataFetcher;
import com.gthoya.graphql.cafe.fetcher.CafeSaveDataFetcher;
import com.gthoya.graphql.cafe.fetcher.CafesDataFetcher;
import com.gthoya.graphql.common.service.ExecuteProvider;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("cafeProvider")
public class CafeProvider implements ExecuteProvider {
    private final CafesDataFetcher cafesDataFetcher;
    private final CafeDataFetcher cafeDataFetcher;
    private final CafeSaveDataFetcher cafeSaveDataFetcher;

    public CafeProvider(CafesDataFetcher cafesDataFetcher, CafeDataFetcher cafeDataFetcher, CafeSaveDataFetcher cafeSaveDataFetcher) {
        this.cafesDataFetcher = cafesDataFetcher;
        this.cafeDataFetcher = cafeDataFetcher;
        this.cafeSaveDataFetcher = cafeSaveDataFetcher;
    }

    private GraphQL graphQL;

    @Value("classpath:cafe.graphql")
    private Resource resource;

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeQuery -> typeQuery
                        .dataFetcher("cafes", cafesDataFetcher)
                        .dataFetcher("cafe", cafeDataFetcher))
                .type("Mutation", typeMutation -> typeMutation
                        .dataFetcher("addCafe", cafeSaveDataFetcher)
                        .dataFetcher("modifyCafe", cafeSaveDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
