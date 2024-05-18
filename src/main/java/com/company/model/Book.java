
package com.company.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String author;
    @Column(unique = true, nullable = false)
    private LocalDate isbn;
    @Column(nullable=false)
    private Integer publishedYear;
    @Column(nullable=false)
    private Double price;
}