package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long> {

    boolean existsByLinkUrl(String linkUrl);
    List<ArticleJpaEntity> findAllByAuthorId(long authorId);
}
