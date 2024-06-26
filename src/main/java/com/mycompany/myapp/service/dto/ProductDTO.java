package com.mycompany.myapp.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Product} entity.
 */
@RegisterForReflection
public class ProductDTO implements Serializable {

    public Long id;

    @NotNull
    public String name;

    @NotNull
    @Size(max = 100)
    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + ", id=" + id + ", name='" + name + "'" + ", description='" + description + "'" + "}";
    }
}
