package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    @SuppressWarnings("null")
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @SuppressWarnings("null")
    public Product updateProduct(Long id, Product product) {
        if (repo.existsById(id)) {
            product.setId(id);
            return repo.save(product);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}

