package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.TestUtil;
import org.junit.jupiter.api.Test;

class ProductDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDTO.class);
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.id = 1L;
        ProductDTO productDTO2 = new ProductDTO();
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO2.id = productDTO1.id;
        assertThat(productDTO1).isEqualTo(productDTO2);
        productDTO2.id = 2L;
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO1.id = null;
        assertThat(productDTO1).isNotEqualTo(productDTO2);
    }
}
