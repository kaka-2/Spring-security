package com.example.demojwt.Product;

import com.example.demojwt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;

    @Override
    public @NotNull Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
