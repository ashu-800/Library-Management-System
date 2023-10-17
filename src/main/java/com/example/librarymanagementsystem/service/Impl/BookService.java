package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responsetDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
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

    public String addBook(Book book) {

        // check if author exists or not
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author id!!!");
        }

        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);  // save both author and book
        return "Book added successfully";

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