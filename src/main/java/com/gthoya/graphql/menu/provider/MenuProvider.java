package com.gthoya.graphql.menu.provider;

import com.gthoya.graphql.menu.fetcher.MenuDataFetcher;
import com.gthoya.graphql.menu.fetcher.MenuRemoveDataFetcher;
import com.gthoya.graphql.menu.fetcher.MenuSaveDataFetcher;
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

@Component
public class MenuProvider implements MenuDetails {
    private final MenuDataFetcher menuDataFetcher;
    private final MenuSaveDataFetcher menuSaveDataFetcher;
    private final MenuRemoveDataFetcher menuRemoveDataFetcher;

    private GraphQL graphQL;

    @Value("classpath:menu.graphql")
    private Resource resource;

    public MenuProvider(MenuDataFetcher menuDataFetcher, MenuSaveDataFetcher menuSaveDataFetcher, MenuRemoveDataFetcher menuRemoveDataFetcher) {
        this.menuDataFetcher = menuDataFetcher;
        this.menuSaveDataFetcher = menuSaveDataFetcher;
        this.menuRemoveDataFetcher = menuRemoveDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeQuery -> typeQuery
                        .dataFetcher("menus", menuDataFetcher))
                .type("Mutation", typeMutation -> typeMutation
                        .dataFetcher("addMenu", menuSaveDataFetcher)
                        .dataFetcher("removeMenu", menuRemoveDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
