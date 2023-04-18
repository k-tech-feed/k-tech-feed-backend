package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import com.gongmeda.ktechfeedbackend.domain.Author;
import org.springframework.stereotype.Component;

@Component
class AuthorMapper {

    public Author toDomain(AuthorJpaEntity authorJpaEntity) {
        return new Author(
            authorJpaEntity.getId(),
            authorJpaEntity.getName(),
            authorJpaEntity.getLogoUrl(),
            authorJpaEntity.getBlogUrl(),
            authorJpaEntity.getRssUrl(),
            authorJpaEntity.getDescription(),
            authorJpaEntity.getLinks()
        );
    }

    public AuthorJpaEntity toJpaEntity(Author author) {
        return new AuthorJpaEntity(
            author.getId(),
            author.getName(),
            author.getLogoUrl(),
            author.getBlogUrl(),
            author.getRssUrl(),
            author.getDescription(),
            author.getLinks()
        );
    }
}
