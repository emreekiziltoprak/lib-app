CREATE TABLE books (
id UUID PRIMARY KEY,
isbn VARCHAR(20) NOT NULL UNIQUE,
total_copies INT NOT NULL CHECK (total_copies > 0),
genre VARCHAR(30) NOT NULL,
publish_year INT NOT NULL,
author VARCHAR(255) NOT NULL,
title VARCHAR(255) NOT NULL
);

CREATE INDEX idx_books_author ON books (author);
CREATE INDEX idx_books_genre ON books (genre);