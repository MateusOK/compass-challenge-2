create table  book(
    id int not null primary key auto_increment,
    book_title varchar(255) not null,
    author varchar(255) not null,
    release_date date not null,
    pages int not null,
    rating double,
    genre varchar(255)

);
INSERT INTO book (book_title, author, release_date, pages, rating, genre)
VALUES
  ('The Great Gatsby', 'F. Scott Fitzgerald', '1925-04-10', 218, 4.5, 'Fiction'),
  ('To Kill a Mockingbird', 'Harper Lee', '1960-07-11', 324, 4.8, 'Fiction'),
  ('1984', 'George Orwell', '1949-06-08', 328, 4.7, 'Fiction'),
  ('Pride and Prejudice', 'Jane Austen', '1813-01-28', 279, 4.6, 'Classic'),
  ('The Lord of the Rings', 'J.R.R. Tolkien', '1954-07-29', 1178, 4.9, 'Fantasy');