package com.kabaldin.product.controller;

import com.kabaldin.product.entity.ProductEntity;
import com.kabaldin.product.ProductRepository;
import com.kabaldin.product.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable("id") Long id, @RequestBody ProductEntity updatedProduct) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(product);
    }
}
