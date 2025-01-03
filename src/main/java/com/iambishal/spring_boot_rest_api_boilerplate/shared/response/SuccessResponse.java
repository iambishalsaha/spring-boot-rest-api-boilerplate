package com.iambishal.spring_boot_rest_api_boilerplate.shared.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This class represents a success response DTO. It inherits from the `AbstractBaseResponse`
 * and adds a `message` field to provide a success message to the client.
 * <p>
 * - `@Schema` annotation from OpenAPI library is used to document the response field
 * for API documentation tools.
 */
@Getter
@Setter
@SuperBuilder
public class SuccessResponse extends AbstractBaseResponse {

    /**
     * The success message to be included in the response.
     * <p>
     * `@Schema` annotation provides details about the message field for API documentation.
     * - Here, we assume the `message` field is a String type for simplicity.
     * - Replace this with the actual type of the `message` field if it's different.
     */
    @Schema(
            name = "message",
            description = "Response message field",
            type = "String",  // Assuming message is a String type for simplicity. Replace with actual type if necessary.
            example = "Operation successful"
    )
    private String message;
}
