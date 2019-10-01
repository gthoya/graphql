package com.gthoya.graphql.menu.service;

import com.gthoya.graphql.common.service.ExecuteProvider;
import com.gthoya.graphql.menu.fetcher.MenusDataFetcher;
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

@Component("menuProvider")
public class MenuProvider implements ExecuteProvider {
    private final MenusDataFetcher menusDataFetcher;
    private final MenuSaveDataFetcher menuSaveDataFetcher;
    private final MenuRemoveDataFetcher menuRemoveDataFetcher;

    private GraphQL graphQL;

    @Value("classpath:menu.graphql")
    private Resource resource;

    public MenuProvider(MenusDataFetcher menusDataFetcher, MenuSaveDataFetcher menuSaveDataFetcher, MenuRemoveDataFetcher menuRemoveDataFetcher) {
        this.menusDataFetcher = menusDataFetcher;
        this.menuSaveDataFetcher = menuSaveDataFetcher;
        this.menuRemoveDataFetcher = menuRemoveDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeQuery -> typeQuery
                        .dataFetcher("menus", menusDataFetcher))
                .type("Mutation", typeMutation -> typeMutation
                        .dataFetcher("addMenu", menuSaveDataFetcher)
                        .dataFetcher("deleteMenu", menuRemoveDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
