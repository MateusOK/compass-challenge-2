CREATE TABLE book (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    pages INT NOT NULL,
    rating DOUBLE,
    genre VARCHAR(255)
);

INSERT INTO book (book_title, author, release_date, pages, rating, genre) VALUES
    ('the great gatsby', 'f. scott fitzgerald', '1925-04-10', 218, 4.5, 'fiction'),
    ('to kill a mockingbird', 'harper lee', '1960-07-11', 324, 7.8, 'fiction'),
    ('1984', 'george orwell', '1949-06-08', 328, 4.7, 'fiction'),
    ('pride and prejudice', 'jane austen', '1813-01-28', 279, 9.6, 'classic'),
    ('the lord of the rings', 'j.r.r. tolkien', '1954-07-29', 1178, 4.9, 'fantasy'),
    ('the catcher in the rye', 'j.d. salinger', '1951-07-16', 234, 6.3, 'fiction'),
    ('to the lighthouse', 'virginia woolf', '1927-05-05', 209, 9.5, 'classic'),
    ('moby-dick', 'herman melville', '1851-10-18', 585, 8.6, 'classic'),
    ('the hobbit', 'j.r.r. tolkien', '1937-09-21', 310, 4.7, 'fantasy'),
    ('the great expectations', 'charles dickens', '1861-08-01', 544, 3.5, 'classic'),
    ('frankenstein', 'mary shelley', '1818-01-01', 280, 4.4, 'classic'),
    ('the adventures of tom sawyer', 'mark twain', '1876-12-01', 224, 2.2, 'fiction'),
    ('brave new world', 'aldous huxley', '1932-01-01', 311, 1.5, 'fiction'),
    ('the alchemist', 'paulo coelho', '1988-01-01', 208, 9.6, 'fiction'),
    ('the picture of dorian gray', 'oscar wilde', '1890-07-01', 254, 5.4, 'fiction'),
    ('one hundred years of solitude', 'gabriel garcia marquez', '1967-06-30', 417, 7.8, 'fiction'),
    ('anna karenina', 'leo tolstoy', '1877-03-01', 864, 6.7, 'classic'),
    ('the count of monte cristo', 'alexandre dumas', '1844-08-28', 1276, 4.8, 'classic'),
    ('the chronicles of narnia', 'c.s. lewis', '1950-10-16', 767, 3.6, 'fantasy'),
    ('harry potter and the sorcerer''s stone', 'j.k. rowling', '1997-06-26', 320, 1.8, 'fantasy'),
    ('the sun also rises', 'ernest hemingway', '1926-10-22', 251, 5.1, 'fiction'),
    ('the scarlet letter', 'nathaniel hawthorne', '1850-03-16', 279, 9.3, 'classic'),
    ('the grapes of wrath', 'john steinbeck', '1939-04-14', 464, 8.5, 'classic'),
    ('the catch-22', 'joseph heller', '1961-11-10', 453, 1.4, 'fiction'),
    ('the handmaid''s tale', 'margaret atwood', '1985-06-14', 311, 2.6, 'fiction'),
    ('the brothers karamazov', 'fyodor dostoevsky', '1880-11-01', 796, 8.7, 'classic');
