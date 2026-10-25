package com.emrekiziltoprak.libraryapp.book;

import com.emrekiziltoprak.libraryapp.utils.Utils;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;


@Getter
public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publishYear;
    private Genre genre;
    private int totalCopies;

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
