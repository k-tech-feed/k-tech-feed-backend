package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

interface ArticleViewRecordRepository extends JpaRepository<ArticleViewRecordJpaEntity, Long> {

    boolean existsByArticleIdAndIpAddressAndCreatedAtGreaterThanEqual(long articleId, String ipAddress, LocalDateTime localDateTime);
}
