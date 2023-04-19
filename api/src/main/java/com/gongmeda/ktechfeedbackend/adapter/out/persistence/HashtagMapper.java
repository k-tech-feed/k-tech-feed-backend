package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import org.springframework.stereotype.Component;

@Component
class HashtagMapper {

    public String toDomain(HashtagJpaEntity hashtagJpaEntity) {
        return hashtagJpaEntity.getName();
    }

    public HashtagJpaEntity toJpaEntity(String hashtag) {
        return new HashtagJpaEntity(hashtag);
    }
}
