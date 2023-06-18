package com.example.demo.domain.lock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //In particular, the query condition must be an index column. If it is not an index,
    // it will become a table lock and lock the entire table.
    @Query(value = "select * from article a where a.id = :id for update", nativeQuery = true)
    Optional<Article> findArticleForUpdate(Long id);
}
