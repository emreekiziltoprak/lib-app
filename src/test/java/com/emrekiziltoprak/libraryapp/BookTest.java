package com.emrekiziltoprak.libraryapp;

import com.emrekiziltoprak.libraryapp.book.Book;
import com.emrekiziltoprak.libraryapp.book.Genre;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;

public class BookTest {

    private Book sampleBook(String isbn){
        return new Book(isbn, 5, Genre.OTHER, 1900, "Franz Kafka", "Milenaya Mektuplar");
    }

    @Test
    void booksWithSameIsbnAreEqual(){
        Set<Book> books = new HashSet<>();
        books.add(sampleBook("123"));
        books.add(sampleBook("123"));

        assertThat(books).hasSize(1);

    }

    @Test
    void cannotRemoveAllCopies() {
        Book book = sampleBook("123");

        assertThatThrownBy(()->
                book.removeCopies(9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
