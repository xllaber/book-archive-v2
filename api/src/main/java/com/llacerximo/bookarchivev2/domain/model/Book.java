package com.llacerximo.bookarchivev2.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    Integer pages;
    @Column(name = "publish_year")
    Integer publishYear;
    @Column(name = "saga_num")
    Double sagaNum;
    Boolean fave;
    String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saga_id")
    Saga saga;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    List<Reread> rereads;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "books_genres",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
        name = "authors_books",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    List<Author> authors;
    
}
