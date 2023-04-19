package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleViewRecordRepository extends JpaRepository<ArticleViewRecordJpaEntity, Long> {

    boolean existsByArticleIdAndIpAddressAndCreatedAtGreaterThanEqual(long articleId, String ipAddress, LocalDateTime localDateTime);
}
