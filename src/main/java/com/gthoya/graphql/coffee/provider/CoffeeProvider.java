package com.gthoya.graphql.coffee.provider;

import com.gthoya.graphql.coffee.fetcher.CoffeeDataFetcher;
import com.gthoya.graphql.coffee.fetcher.CoffeeMutationDataFetcher;
import com.gthoya.graphql.coffee.fetcher.CoffeesDataFetcher;
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
public class CoffeeProvider implements CoffeeDetails {
    private final CoffeesDataFetcher coffeesDataFetcher;
    private final CoffeeDataFetcher coffeeDataFetcher;
    private final CoffeeMutationDataFetcher coffeeMutationDataFetcher;

    private GraphQL graphQL;

    @Value("classpath:coffee.graphql")
    private Resource resource;

    public CoffeeProvider(CoffeesDataFetcher coffeesDataFetcher, CoffeeDataFetcher coffeeDataFetcher, CoffeeMutationDataFetcher coffeeMutationDataFetcher) {
        this.coffeesDataFetcher = coffeesDataFetcher;
        this.coffeeDataFetcher = coffeeDataFetcher;
        this.coffeeMutationDataFetcher = coffeeMutationDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeQuery -> typeQuery
                        .dataFetcher("coffees", coffeesDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .type("Mutation", typeMutation -> typeMutation
                        .dataFetcher("addCoffee", coffeeMutationDataFetcher)
                        .dataFetcher("modifyCoffee", coffeeMutationDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
