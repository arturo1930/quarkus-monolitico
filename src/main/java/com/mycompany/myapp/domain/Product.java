package com.mycompany.myapp.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cacheable
@RegisterForReflection
public class Product extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    public String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    public String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + "'" + ", description='" + description + "'" + "}";
    }

    public Product update() {
        return update(this);
    }

    public Product persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product can't be null");
        }
        var entity = Product.<Product>findById(product.id);
        if (entity != null) {
            entity.name = product.name;
            entity.description = product.description;
        }
        return entity;
    }

    public static Product persistOrUpdate(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product can't be null");
        }
        if (product.id == null) {
            persist(product);
            return product;
        } else {
            return update(product);
        }
    }
}
