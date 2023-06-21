package com.kabaldin.product;

import com.kabaldin.product.controller.ProductController;
import com.kabaldin.product.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        ProductEntity product1 = new ProductEntity();
        ProductEntity product2 = new ProductEntity();

        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(10.99);
        product1.setQuantity(50);

        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(15.99);
        product2.setQuantity(100);
        List<ProductEntity> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].price").value(10.99))
                .andExpect(jsonPath("$[0].quantity").value(50))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].price").value(15.99))
                .andExpect(jsonPath("$[1].quantity").value(100));
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(10.99);
        product.setQuantity(50);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Product 1\", \"description\": \"Description 1\", \"price\": 10.99, \"quantity\": 50 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.price").value(10.99))
                .andExpect(jsonPath("$.quantity").value(50));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductEntity product = new ProductEntity();
        ProductEntity updatedProduct = new ProductEntity();

        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(10.99);
        product.setQuantity(50);

        updatedProduct.setId(1L); // Corrected ID to match the product being updated
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(15.99);
        updatedProduct.setQuantity(100);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Updated Product\", \"description\": \"Updated Description\", \"price\": 15.99, \"quantity\": 100 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.price").value(15.99))
                .andExpect(jsonPath("$.quantity").value(100));
    }
}
