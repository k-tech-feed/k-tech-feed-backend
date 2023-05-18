package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long> {

    boolean existsByLinkUrl(String linkUrl);

    List<ArticleJpaEntity> findAllByAuthorId(long authorId);
}
