package com.book.book.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "acronym", nullable = false, unique = true)
    private String acronym;

    @JsonBackReference
    @ToString.Exclude
    @ManyToMany(mappedBy = "languages", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Book> books = new HashSet<>();

    public Language() {
    }


}
