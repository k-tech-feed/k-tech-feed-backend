package com.gongmeda.ktechfeedbackend.application.service;

import com.gongmeda.ktechfeedbackend.application.port.in.AddAuthorCommand;
import com.gongmeda.ktechfeedbackend.application.port.in.AuthorUseCase;
import com.gongmeda.ktechfeedbackend.application.port.in.UpdateAuthorCommand;
import com.gongmeda.ktechfeedbackend.application.port.out.AuthorPersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Author;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class AuthorService implements AuthorUseCase {

    private final AuthorPersistencePort authorPersistencePort;

    @Override
    public List<Author> getAllAuthors() {
        return authorPersistencePort.getAll();
    }

    @Override
    public Author getAuthor(long authorId) {
        return authorPersistencePort.getById(authorId)
            .orElseThrow(RuntimeException::new); // TODO: 커스텀 예외 처리
    }

    @Override
    public void addAuthor(AddAuthorCommand addAuthorCommand) {
        Author author = new Author(
            addAuthorCommand.getName(),
            addAuthorCommand.getLogoUrl(),
            addAuthorCommand.getBlogUrl(),
            addAuthorCommand.getRssUrl(),
            addAuthorCommand.getDescription(),
            addAuthorCommand.getLinks()
        );
        authorPersistencePort.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthor(long authorId) {
        Author author = authorPersistencePort.getById(authorId)
            .orElseThrow(RuntimeException::new);    // TODO: 커스텀 예외 처리
        authorPersistencePort.delete(author);
    }

    @Override
    public void updateAuthor(long authorId, UpdateAuthorCommand updateAuthorCommand) {
        Author author = authorPersistencePort.getById(authorId)
            .orElseThrow(RuntimeException::new);    // TODO: 커스텀 예외 처리

        author.update(
            updateAuthorCommand.getName(),
            updateAuthorCommand.getLogoUrl(),
            updateAuthorCommand.getBlogUrl(),
            updateAuthorCommand.getRSSUrl(),
            updateAuthorCommand.getDescription(),
            updateAuthorCommand.getLinks()
        );

        authorPersistencePort.save(author);
    }
}
