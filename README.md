# Library | Compass 2nd Challange

## About

The Library project is a RESTful API designed to manage books in a library. It provides a set of endpoints that allow users to perform CRUD (Create, Read, Update, Delete) operations on book data. 
This project was developed as part of the Compass 2nd Challenge, offered by @Compass.UOL.

The main goal of this project is to demonstrate proficiency in building a scalable and maintainable API using Java and Spring Boot. 
It incorporates industry-standard practices and leverages the power of Spring Boot's framework to create a robust and efficient solution. 
The API provides intuitive endpoints for interacting with book data, including the ability to create new books, retrieve existing books,
update book information, and delete books from the library.


## How to Prepare the Environment?

### IDE

To begin, it's important to have an Integrated Development Environment (IDE) installed on your machine. You can choose to install IntelliJ IDEA, 
which is a popular IDE for Java development. You can download and install IntelliJ IDEA by visiting their official [website](https://www.jetbrains.com/idea/download/).

If you come across any issues or face challenges during the installation process, you can refer to a helpful video tutorial on YouTube that provides step-by-step instructions on how to install the IDE.

[![Video](https://i.ytimg.com/vi/viNG3VVnzFE/hq720.jpg)](https://www.youtube.com/watch?v=viNG3VVnzFE)

---

### Attention!

Once you have your IDE installed, it's essential to ensure that you have the Java JDK (Java Development Kit) installed as well. When you create a new project in IntelliJ IDEA, it provides an option to install the JDK automatically.

It's important to note that you should install version 17 or a more recent version of the JDK to ensure compatibility with the project.

By verifying the installation of the Java JDK and ensuring that it's at least version 17, you'll be ready to proceed with your development tasks using IntelliJ IDEA.

![image](https://github.com/MateusOK/ecommerce-compass-challenge-1/assets/84550655/e18ad64e-d7d8-4d14-b4dd-d2edc7506c67)

---

### Postman

Almost ready! Once you have the IDE installed, the next step is to install Postman, which is a popular tool for testing API endpoints. To download Postman, simply visit their official website at [https://www.postman.com/downloads/](https://www.postman.com/downloads/) and follow the instructions provided.

By installing Postman, you'll have a user-friendly interface that allows you to send requests to your API and inspect the responses. It's a valuable tool for testing and debugging API endpoints.

Make sure to have Postman installed before proceeding with testing the API.  

---

## All set!

Now you're all set to test the API! To get started, simply run the application and use the endpoints provided below. Enjoy exploring the functionalities!

---

## Endpoints

### • GET

#### GET all books: 

  To get all books in the database you'll need to send a GET request to
  
```
  http://localhost:8080/api/books
```

---

#### GET book by id: 

  To retrieve a book by its ID, you can send a GET request to
  
```
  http://localhost:8080/api/books/{id}
```
Make sure to replace *{id}* with your desired ID.

  Example:

      http://localhost:8080/api/books/15

---
    
#### GET book by genre: 

  To retrieve a book by its genre, you can send a GET request to
  
```
  http://localhost:8080/api/books/genre?genre={genre}
```
Make sure to replace *{genre}* with your desired genre.

  Example:

      http://localhost:8080/api/books/genre?genre=fiction

---

#### GET book by author: 

  To retrieve a book by its author, you can send a GET request to
  
```
  http://localhost:8080/api/books/author?author={author}
```
Make sure to replace *{author}* with your desired genre.

  Example:

      http://localhost:8080/api/books/author?author=mary shelley

### • POST

#### POST a book:

  To save a new book you'll need to send a POST request to

```
  http://localhost:8080/api/books
```
  Along the request a JSON body is required

  JSON Example:

```json
{
  "bookTitle": "harry potter and the half-blood prince",
  "author": "j.k rowling",
  "releaseDate": "2003-02-02",
  "pages": 1141,
  "rating": 9.3,
  "genre": "fantasy"
}
```
---

#### POST multiple books:

  To save multiple books you'll need to send a POST request to

```
  http://localhost:8080/api/books/batch
```
  Along the request a JSON body is required

  JSON Example:

```json
[
    {
        "bookTitle": "harry potter and chamber of secrets",
        "author": "j.k rowling",
        "releaseDate": "1999-01-01",
        "pages": 227,
        "rating": 7.9,
        "genre": "fantasy"
    },
    {
        "bookTitle": "harry potter and prisoner of azkaban",
        "author": "j.k rowling",
        "releaseDate": "2000-02-02",
        "pages": 345,
        "rating": 8.3,
        "genre": "fantasy"
    }
]
```

### • PUT

### Update a book:

  To update an existing book you'll need to provide the ID of the desired book and a JSON body, to do it just send a PUT request to

```
  http://localhost:8080/api/books/{id}
```

Make sure to replace *{id}* with the book ID and along with it to send a JSON body.

---

### • DELETE

#### DELETE a book:

  To delete an existing book, you'll just need to provide the book ID that you want to delete and send a DELETE request to:

```
  http://localhost:8080/api/books/{id}
```
Make sure to replace *{id}* with the book ID you want to delete.

If the deletetion was successful you'll receive a http status of: **204 No Content**

---

# Technologies

In this project, the following techlogies were used:

- Java
- SpringBoot
- H2
- Maven

---

# Thanks!

We would like to express our gratitude to @Compass.UOL for giving us this amazing opportunity to showcase our skills and knowledge. We are truly grateful for this challenge and look forward to embracing future opportunities.

We extend our heartfelt thanks to the entire team at @Compass.UOL for their support and guidance throughout this process. It has been an incredible learning experience, and we appreciate the chance to put our abilities to the test.

We are excited about the challenges that lie ahead and are eager to contribute our best to the success of @Compass.UOL. Thank you once again for this wonderful opportunity, and we eagerly await what the future holds.


## Authors

Developed by:<br><br>
<a href="https://www.linkedin.com/in/mateus-ribeiro-1779491a7/">Mateus Ribeiro</a><br>
<a href="https://www.linkedin.com/in/lucasrdamaso/">Lucas Damaso</a><br>
<a href="https://www.linkedin.com/in/emillysfranca/">Emilly França</a><br>
<a href="https://www.linkedin.com/in/mateus-silva-ara%C3%BAjo-187586217/">Mateus Silva Araújo</a>
