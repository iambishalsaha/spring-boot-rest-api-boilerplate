package com.iambishal.spring_boot_rest_api_boilerplate.shared.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;

/**
 * This is an abstract base class for all response DTOs in the application.
 * It provides common configurations for Jackson serialization:
 * <p>
 * - `@JsonInclude(JsonInclude.Include.NON_NULL)`:
 * - Excludes fields with null values from the JSON output.
 * - This helps to minimize the size of the JSON responses.
 * <p>
 * - `@JsonIgnoreProperties(ignoreUnknown = true)`:
 * - Ignores unknown properties during deserialization from JSON.
 * - This helps to prevent issues when the JSON response contains unexpected fields.
 * <p>
 * - `@SuperBuilder`:
 * - Provides a convenient way to create immutable objects using the builder pattern.
 * - This promotes immutability and improves code readability.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public abstract class AbstractBaseResponse {

    protected AbstractBaseResponse() {
    }
}
