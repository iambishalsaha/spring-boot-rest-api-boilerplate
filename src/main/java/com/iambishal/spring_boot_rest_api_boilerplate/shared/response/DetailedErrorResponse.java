package com.iambishal.spring_boot_rest_api_boilerplate.shared.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * This class represents a detailed error response DTO. It inherits from the `ErrorResponse`
 * and adds an `items` field to provide a map of specific error messages for different fields.
 * <p>
 * - `@Schema` annotation from OpenAPI library is used to document the response field
 * for API documentation tools.
 */
@Getter
@Setter
@SuperBuilder
public class DetailedErrorResponse extends ErrorResponse {

    /**
     * A map of field names to their corresponding error messages.
     * <p>
     * `@Schema` annotation provides details about the items field for API documentation.
     */
    @Schema(
            name = "items",
            description = "Additional error details",
            type = "Map",
            nullable = true,
            example = "{'field1': 'error message for field1', 'field2': 'error message for field2'}"
    )
    private Map<String, String> items;
}
