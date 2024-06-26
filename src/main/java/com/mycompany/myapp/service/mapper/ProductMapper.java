package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "jakarta", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.id = id;
        return product;
    }
}
