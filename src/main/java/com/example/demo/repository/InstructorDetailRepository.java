package com.example.demo.repository;

import com.example.demo.domain.mapping.Instructor;
import com.example.demo.domain.mapping.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {
}
