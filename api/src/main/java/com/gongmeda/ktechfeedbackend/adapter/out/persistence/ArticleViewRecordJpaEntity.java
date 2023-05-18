package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
class ArticleViewRecordJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ArticleViewRecordJpaEntity(Long articleId, String ipAddress, LocalDateTime createdAt) {
        this.articleId = articleId;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }
}
