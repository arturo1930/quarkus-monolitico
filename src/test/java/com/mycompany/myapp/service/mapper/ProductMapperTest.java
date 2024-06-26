package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.ProductAsserts.*;
import static com.mycompany.myapp.domain.ProductTestSamples.*;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ProductMapperTest {

    @Inject
    ProductMapper productMapper;

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getProductSample1();
        var actual = productMapper.toEntity(productMapper.toDto(expected));
        assertProductAllPropertiesEquals(expected, actual);
    }
}
