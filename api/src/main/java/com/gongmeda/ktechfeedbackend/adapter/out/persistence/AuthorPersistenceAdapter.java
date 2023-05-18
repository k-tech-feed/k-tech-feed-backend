package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import com.gongmeda.ktechfeedbackend.application.port.out.AuthorPersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class AuthorPersistenceAdapter implements AuthorPersistencePort {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDomain).toList();
    }

    @Override
    public Optional<Author> getById(long authorId) {
        return authorRepository.findById(authorId).map(authorMapper::toDomain);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(authorMapper.toJpaEntity(author));
    }

    @Override
    public void delete(Author author) {
        articleRepository.deleteAll(articleRepository.findAllByAuthorId(author.getId()));
        authorRepository.delete(authorMapper.toJpaEntity(author));
    }
}
