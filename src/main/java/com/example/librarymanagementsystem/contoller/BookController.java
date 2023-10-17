package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responsetDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){

        try{
            String response = bookService.addBook(book);
            return response;
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    // delete a book

    // give me names of all the books of a particular genre

    // give me names of all the books of a particular genre and cost gretaer than x rs
    @GetMapping("/get-by-genre-cost")
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre,
                                                                @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThan(genre,cost);

    }

    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre,
                                                                   @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

    }

    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre


}
