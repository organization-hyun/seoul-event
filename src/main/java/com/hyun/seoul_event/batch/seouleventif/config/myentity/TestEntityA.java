package com.hyun.seoul_event.batch.seouleventif.config.myentity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class TestEntityA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private final LocalDateTime createdAt = LocalDateTime.now();

    public TestEntityA(String name) {
        this.name = name;
    }
}
