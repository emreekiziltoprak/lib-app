package com.emrekiziltoprak.libraryapp.book;

import com.emrekiziltoprak.libraryapp.utils.Utils;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
public class Book {

    @Id
    private UUID id;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int publishYear;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column
    private int totalCopies;

    protected Book () {

    }

    public Book(String isbn, int totalCopies, Genre genre, int publishYear, String author, String title) {

        Utils.requireNotBlank(isbn, "isbn");
        Utils.requireNotBlank(title, "title");
        Utils.requireNotBlank(author, "author");
        requireGenre(genre);

        Utils.requireValidYear(publishYear);

        if(totalCopies < 1) {
            throw new IllegalArgumentException("total copies should be at least 1");
        }
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.genre = genre;
        this.publishYear = publishYear;
        this.author = author;
        this.title = title;
        this.id = java.util.UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
    if(this == o) return true;
    if(!(o instanceof Book other)) return false;
    return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", genre=" + genre +
                ", totalCopies=" + totalCopies +
                '}';
    }

    private static Genre requireGenre(Genre genre){
        if(genre == null) {
            throw new IllegalArgumentException("genre mustnt be null");
        }
        return genre;
    }

    public void addCopies(int count) {

        if(count <1) {
            throw new IllegalArgumentException("count cant be smaller than 1");
        }

        this.totalCopies += count;

    }

    public void removeCopies(int count) {

        if(count <1||count>=totalCopies) {
            throw new IllegalArgumentException("count cant be smaller than 1 or bigger than totalcopies");
        }

        this.totalCopies -= count;
    }

    public void updateDetails(String title, String author, int publishYear, Genre genre) {

        Utils.requireNotBlank(title, "title");
        Utils.requireNotBlank(author, "author");
       requireGenre(genre);

        Utils.requireValidYear(publishYear);

        this.genre = genre;
        this.publishYear = publishYear;
        this.author = author;
        this.title = title;
    }

}
