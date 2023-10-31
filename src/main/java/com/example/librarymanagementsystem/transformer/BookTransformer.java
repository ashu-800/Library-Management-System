package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;

public class BookTransformer {

    public static Book BookRequestToBook(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .noOfPages(bookRequest.getNoOfPages())
                .genre(bookRequest.getGenre())
                .cost(bookRequest.getCost())
                .build();
    }

    public static BookResponse BookToBookResponse(Book book){

        return BookResponse.builder()
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .title(book.getTitle())
                .build();
    }
}