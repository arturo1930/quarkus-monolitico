package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.service.Paged;
import com.mycompany.myapp.service.ProductService;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.service.mapper.ProductMapper;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDTO persistOrUpdate(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        var product = productMapper.toEntity(productDTO);
        product = Product.persistOrUpdate(product);
        return productMapper.toDto(product);
    }

    /**
     * Delete the Product by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        Product.findByIdOptional(id).ifPresent(product -> {
            product.delete();
        });
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return Product.findByIdOptional(id).map(product -> productMapper.toDto((Product) product));
    }

    /**
     * Get all the products.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<ProductDTO> findAll(Page page) {
        log.debug("Request to get all Products");
        return new Paged<>(Product.findAll().page(page)).map(product -> productMapper.toDto((Product) product));
    }
}
