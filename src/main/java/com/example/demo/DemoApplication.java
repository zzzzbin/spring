package com.example.demo;

import com.example.demo.domain.mapping.Instructor;
import com.example.demo.domain.mapping.InstructorDetail;
import com.example.demo.domain.taco.Ingredient;
import com.example.demo.dto.Droid;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.domain.taco.Ingredient.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class DemoApplication implements CommandLineRunner {

    @Bean
    @ConfigurationProperties(prefix = "droid")
    Droid createDroid() {
        return new Droid();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    IngredientRepository repo;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public void run(String... args) throws Exception {
        Instructor instructor = new Instructor("Trung", "Nguyen", "trungnguyen@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://trungnguyen.com", "Love to code");
        instructor.setInstructorDetail(instructorDetail);
        instructorRepository.save(instructor);
        instructorRepository.delete(instructor);
        repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }
}
