package com.eventos.config;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomiser operationIdCustomiser() {
        return openApi -> openApi.getPaths().values().stream()
                .flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    String operationId = operation.getOperationId();
                    if (operationId != null && operationId.startsWith("_")) {
                        operation.setOperationId(operationId.substring(1));
                    }
                });
    }
}
