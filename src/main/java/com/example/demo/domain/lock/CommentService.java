package com.example.demo.domain.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional //You can only lock something in the context of a database transaction.
    public void postComment(Long articleId, String content) {
//        Optional<Article> articleOptional = articleRepository.findById(articleId);
//        Optional<Article> articleOptional = articleRepository.findArticleForUpdate(articleId);
        Optional<Article> articleOptional = articleRepository.findArticleWithPessimisticLock(articleId);

        if (!articleOptional.isPresent()) {
            throw new RuntimeException("no corresponding article");
        }
        Article article = articleOptional.get();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        commentRepository.save(comment);

        article.setCommentCount(article.getCommentCount() + 1);
        articleRepository.save(article);
    }
}
