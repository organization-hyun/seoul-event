package com.hyun.seoul_event.batch.seouleventif.repository;

import com.hyun.seoul_event.batch.seouleventif.SeoulEventIf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeoulEventIfRepository extends JpaRepository<SeoulEventIf, Long> {
}
