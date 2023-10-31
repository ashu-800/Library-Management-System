package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.transformer.AuthorTransformer;
import com.example.librarymanagementsystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    public String addBook(BookRequest bookRequest) {
        Optional<Author> authorOptional=authorRepository.findById(bookRequest.getAuthorId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid Author Id!");
        }
        Author author= authorOptional.get();
        Book book= BookTransformer.BookRequestToBook(bookRequest);
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);   // ths will save both book and author as it is cascaded
        return "Book Saved Successfully!";
    }

    public String deleteBook(int id) {
        Optional<Book> bookOptional= bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            throw new BookNotAvailableException("Invalid Book Id!");
        }
        bookRepository.deleteById(id);
        return "Book Deleted Successfully!";
    }

    public List<BookResponse> getBookByGenre(Genre genre) {
        List<Book> books=bookRepository.findByGenre(genre);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotAvailableException("No books available of required genre!");
        }
        return bookResponses;
    }

    public List<BookResponse> GetBookByGenreAndCostMoreThanX(Genre genre, double x) {
        List<Book> books=bookRepository.findBookByGenreAndCostMoreThanX(genre, x);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotAvailableException("No books available of required type!");
        }
        return bookResponses;
    }
    public List<BookResponse> GetBooksHavingPagesBetweenAAndB(int a, int b) {
        List<Book> books=bookRepository.findBooksHavingPagesBetweenAAndB(a, b);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotAvailableException("No books available of required type!");
        }
        return bookResponses;
    }

    public List<AuthorResponse> GetAllAuthorsWithGenreX(Genre genre) {
        List<Author> authors=bookRepository.findAllAuthorsWithGenreX(genre);
        List<AuthorResponse> authorResponses= new ArrayList<>();
        for (Author author: authors){
            authorResponses.add(AuthorTransformer.AuthorToAuthorResponse(author));
        }
        if (authorResponses.isEmpty()){
            throw new AuthorNotFoundException("No author present with specific genre!");
        }
        return authorResponses;
    }
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);

        // prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

        // prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }
}