package com.example.demo.repository;

import com.example.demo.domain.taco.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}
