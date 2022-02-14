package com.example.demo.repository.ecommerce;

import com.example.demo.domain.ecommerce.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MProductRepository extends JpaRepository<MProduct, Long> {
}
