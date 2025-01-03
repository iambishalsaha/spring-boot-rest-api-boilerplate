package com.iambishal.spring_boot_rest_api_boilerplate.shared.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This class represents a pagination response DTO. It inherits from the `AbstractBaseResponse`
 * and provides information about a paginated set of data.
 * <p>
 * - `@Schema` and `@ArraySchema` annotations from OpenAPI library are used to document
 * the response fields for API documentation tools.
 */
@Getter
@Setter
public class PaginationResponse<T> extends AbstractBaseResponse {

    /**
     * The current page number (starts from 1).
     * <p>
     * `@Schema` annotation provides details about the current_page field for API documentation.
     */
    @Schema(
            name = "current_page",
            description = "Current page number",
            type = "Integer",
            example = "1"
    )
    private Integer currentPage;

    /**
     * The total number of pages available.
     * <p>
     * `@Schema` annotation provides details about the total_page field for API documentation.
     */
    @Schema(
            name = "total_page",
            description = "Total number of page",
            type = "Integer",
            example = "10"
    )
    private Integer totalPages;
    /**
     * The number of records included in each page.
     * <p>
     * `@Schema` annotation provides details about the page_size field for API documentation.
     */
    @Schema(
            name = "page_size",
            description = "Total number of records in a page",
            type = "Integer",
            example = "25"
    )
    private Integer pageSize;

    /**
     * The total number of records in the entire dataset.
     * <p>
     * `@Schema` annotation provides details about the total_items field for API documentation.
     */
    @Schema(
            name = "total_items",
            description = "Total number of records in the entire dataset",
            type = "Long",
            example = "100"
    )
    private Long totalItems;

    /**
     * The list of items for the current page.
     * <p>
     * `@ArraySchema` annotation provides details about the items field for API documentation.
     * - The schema property defines the type parameter `T` for the list items.
     */
    @ArraySchema(
            schema = @Schema(
                    type = "T",
                    description = "items"
            )
    )
    private List<T> items;

    /**
     * Constructor that takes a Spring Data `Page` object and a list of converted items
     * and populates the corresponding fields in the DTO.
     *
     * @param pageModel the Spring Data Page object containing pagination information
     * @param items     the list of items for the current page after conversion (if needed)
     */
    public PaginationResponse(final Page<?> pageModel, final List<T> items) {
        this.currentPage = pageModel.getNumber() + 1;
        this.totalPages = pageModel.getTotalPages();
        this.pageSize = pageModel.getSize();
        this.totalItems = pageModel.getTotalElements();
        this.items = items;
    }
}
