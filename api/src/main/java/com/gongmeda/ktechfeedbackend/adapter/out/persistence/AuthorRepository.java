package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuthorRepository extends JpaRepository<AuthorJpaEntity, Long> {

}
