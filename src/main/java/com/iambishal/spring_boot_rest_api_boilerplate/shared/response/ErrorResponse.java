package com.iambishal.spring_boot_rest_api_boilerplate.shared.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This class represents an error response DTO. It inherits from the `AbstractBaseResponse`
 * and adds a `message` field to provide more information about the error.
 * <p>
 * - `@Schema` annotation from OpenAPI library is used to document the response field
 * for API documentation tools.
 */
@Getter
@Setter
@SuperBuilder
public class ErrorResponse extends AbstractBaseResponse {

    /**
     * The error message to be included in the response.
     * <p>
     * `@Schema` annotation provides details about the message field for API documentation.
     */
    @Schema(
            name = "message",
            description = "Response message field",
            type = "String",
            example = "An error occurred while processing your request."
    )
    private String message;
}
