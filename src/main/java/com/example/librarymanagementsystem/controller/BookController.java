package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest ){

        try {
            String response = bookService.addBook(bookRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);

        }
        catch(AuthorNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }

    // delete a book
    @DeleteMapping("/delete-bookid=/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try {
            String response = bookService.deleteBook(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (BookNotAvailableException e){
            String response =e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    // give me names of all the books of a particular genre
    @GetMapping("/get-by-genre")
    public ResponseEntity getBookByGenre(@RequestParam Genre genre){
        try{
            List<BookResponse> books= bookService.getBookByGenre(genre);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotAvailableException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }


    }

    // give me names of all the books of a particular genre and cost gretaer than x rs
    @GetMapping("/get-by-genre-and-cost-greater-than-x")
    public ResponseEntity GetBookByGenreAndCostMoreThanX(@RequestParam Genre genre, @RequestParam double x){

        try{
            List<BookResponse> books= bookService.GetBookByGenreAndCostMoreThanX(genre,x);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotAvailableException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre,
                                                                   @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

    }

    // give me all the books having number of pages between 'a' and 'b'
    @GetMapping("/all-books-having-pages-a-to-b")
    public ResponseEntity GetBooksHavingPagesBetweenAAndB(@RequestParam int a, @RequestParam int b){

        try{
            List<BookResponse> books= bookService.GetBooksHavingPagesBetweenAAndB(a, b);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotAvailableException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    // give me the names of all the authors who write a particular genre

    @GetMapping("/all-authors-with-genre=/{genre}")
    public ResponseEntity GetAllAuthorsWithGenreX(@PathVariable Genre genre){

        try{
            List<AuthorResponse> authors= bookService.GetAllAuthorsWithGenreX(genre);
            return new ResponseEntity(authors, HttpStatus.FOUND);
        }
        catch (AuthorNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }
}
