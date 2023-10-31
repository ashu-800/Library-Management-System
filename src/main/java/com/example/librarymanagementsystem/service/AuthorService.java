package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.transformer.AuthorTransformer;
import com.example.librarymanagementsystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService  {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author= AuthorTransformer.AuthorRequestToAuthor(authorRequest);
        Author savedAuthor= authorRepository.save(author);
        return AuthorTransformer.AuthorToAuthorResponse(savedAuthor);
    }
    public AuthorResponse updateEmail(int id, String newEmail) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author Id!");
        }
        Author author= authorOptional.get();
        author.setEmailId(newEmail);

        Author savedAuthor= authorRepository.save(author);

        return AuthorTransformer.AuthorToAuthorResponse(savedAuthor);
    }

    public List<BookResponse> getAllBookWrittenByX(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author Id!");
        }
        Author author= authorOptional.get();
        if(author.getBooks().size()==0){
            throw new BookNotAvailableException("Author Does Not Registered Any Book Yet!");
        }
        List<BookResponse> response= new ArrayList<>();
        for(Book book:author.getBooks()){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }

    public List<AuthorResponse> getAuthorWithMoreThanXBooks(int x) {
        List<Author> authors= new ArrayList<>();
        authors= authorRepository.getAuthorWithMoreThanXBooks(x);
        List<AuthorResponse> response= new ArrayList<>();
        for (Author author: authors){
            response.add(AuthorTransformer.AuthorToAuthorResponse(author));
        }
        return response;

    }
}
