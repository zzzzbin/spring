package com.example.demo.repository.ecommerce;

import com.example.demo.domain.ecommerce.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface MProductRepository extends JpaRepository<MProduct, Long> {
}
