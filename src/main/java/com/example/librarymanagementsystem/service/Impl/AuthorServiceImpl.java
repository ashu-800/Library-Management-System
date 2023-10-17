package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "Author Successfully Added!!!";
    }
}
