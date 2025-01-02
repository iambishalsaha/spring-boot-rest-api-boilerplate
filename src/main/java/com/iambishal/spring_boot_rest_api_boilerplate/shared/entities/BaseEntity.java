package com.iambishal.spring_boot_rest_api_boilerplate.shared.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * BaseEntity is an abstract class that provides common properties for
 * all entities in the application. It serves as a foundation for other
 * entity classes by defining shared fields like `id`, `createdAt`, and `updatedAt`.
 * <p>
 * It is annotated with `@MappedSuperclass` to indicate that its properties
 * will be inherited by subclasses without creating a database table for it.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * The ID of the entity. It's a UUID type and is generated automatically using the
     * GenerationType.UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The timestamp of when the entity was created. This is automatically populated
     * when a new entity is persisted using the CreationTimestamp annotation.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp of when the entity was last updated. This is automatically updated
     * whenever the entity is persisted using the UpdateTimestamp annotation.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
