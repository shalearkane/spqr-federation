package customMapExposedSchema;

import graphql.introspection.Introspection;
import graphql.schema.*;
import io.leangen.graphql.GraphQLSchemaGenerator;

public class MapExposedSchema {
    private static final GraphQLScalarType UNREPRESENTABLE = GraphQLScalarType.newScalar()
            .name("UNREPRESENTABLE")
            .description("Use SPQR's SchemaPrinter to remove this from SDL")
            .coercing(new Coercing<Object, String>() {
                private static final String ERROR = "Type not intended for use";

                @Override
                public String serialize(Object dataFetcherResult) {
                    return "__internal__";
                }

                @Override
                public Object parseValue(Object input) {
                    throw new CoercingParseValueException(ERROR);
                }

                @Override
                public Object parseLiteral(Object input) {
                    throw new CoercingParseLiteralException(ERROR);
                }
            })
            .build();

    public static GraphQLSchema customSchema(Object query, String basePackageName) {

        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages(basePackageName)
                .withOperationsFromSingletons(query)
                .generate();

        // UNREPRESENTABLE scalar
        GraphQLScalarType unrepresentableScalar = UNREPRESENTABLE;

        // _mappedOperation directive definition
        GraphQLDirective mappedOperationDirective = GraphQLDirective.newDirective()
                .name("_mappedOperation")
                .description("")
                .validLocation(Introspection.DirectiveLocation.FIELD_DEFINITION)
                .argument(GraphQLArgument.newArgument()
                        .name("operation")
                        .description("")
                        .type(unrepresentableScalar)
                        .build()
                )
                .build();

        // _mappedInputField directive definition
        GraphQLDirective mappedInputFieldDirective = GraphQLDirective.newDirective()
                .name("_mappedInputField")
                .description("")
                .validLocation(Introspection.DirectiveLocation.INPUT_FIELD_DEFINITION)
                .argument(GraphQLArgument.newArgument()
                        .name("inputField")
                        .description("")
                        .type(unrepresentableScalar)
                        .build()
                )
                .build();

        // _mappedType directive definition
        GraphQLDirective mappedTypeDirective = GraphQLDirective.newDirective()
                .name("_mappedType")
                .description("")
                .validLocations(Introspection.DirectiveLocation.INPUT_OBJECT, Introspection.DirectiveLocation.OBJECT)
                .argument(GraphQLArgument.newArgument()
                        .name("type")
                        .description("")
                        .type(unrepresentableScalar)
                        .build()
                )
                .build();

        // Add new definitions to schema
        GraphQLSchema newSchema = GraphQLSchema.newSchema(schema)
                .additionalDirective(mappedTypeDirective)
                .additionalDirective(mappedOperationDirective)
                .additionalDirective(mappedInputFieldDirective)
                .build();

        return newSchema;
    }
}
