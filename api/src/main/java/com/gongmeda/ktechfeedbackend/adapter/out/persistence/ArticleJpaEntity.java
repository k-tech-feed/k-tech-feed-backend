package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(columnList = "timestamp"),})
@Entity
class ArticleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private AuthorJpaEntity author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = false, unique = true)
    private String linkUrl;
    private String thumbnailUrl;
    private LocalDateTime timestamp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "article_hashtag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<HashtagJpaEntity> hashtags = new ArrayList<>();
}
