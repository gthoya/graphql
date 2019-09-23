package com.gthoya.graphql.coffee.provider;

import com.gthoya.graphql.coffee.dao.CoffeeRepository;
import com.gthoya.graphql.coffee.model.Coffee;
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
import java.util.stream.Stream;

@Component
public class CoffeeProvider implements CoffeeDetails {
    private final CoffeeRepository coffeeRepository;
    private final CoffeesDataFetcher coffeesDataFetcher;
    private final CoffeeDataFetcher coffeeDataFetcher;

    private GraphQL graphQL;

    @Value("classpath:coffees.graphql")
    private Resource resource;

    public CoffeeProvider(CoffeeRepository coffeeRepository, CoffeesDataFetcher coffeesDataFetcher, CoffeeDataFetcher coffeeDataFetcher) {
        this.coffeeRepository = coffeeRepository;
        this.coffeesDataFetcher = coffeesDataFetcher;
        this.coffeeDataFetcher = coffeeDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataIntoHSQL();

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("coffees", coffeesDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                new Coffee("1", "americano"),
                new Coffee("2", "caffe latte"),
                new Coffee("3", "caramel macchiato")
        ).forEach(coffeeRepository::save);
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
