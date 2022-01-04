package com.example.demo.rest;

import com.example.demo.domain.product.model.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException() {
        return ResponseEntity.ok("Product not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product p = productRepository.findById(product.getId()).get();
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        Product saveProduct = productRepository.save(p);
        return ResponseEntity.ok(saveProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/paging")
    public ResponseEntity<List<Product>> getProductPagination(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> productList = productRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(productList);
    }
}
