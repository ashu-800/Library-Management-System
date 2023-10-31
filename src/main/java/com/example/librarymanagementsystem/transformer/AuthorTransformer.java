package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;

public class AuthorTransformer {
    public  static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .age(authorRequest.getAge())
                .emailId(authorRequest.getEmail())
                .name(authorRequest.getName())
                .build();
    }
    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .name(author.getName())
                .emailID(author.getEmailId())
                .build();
    }
}
