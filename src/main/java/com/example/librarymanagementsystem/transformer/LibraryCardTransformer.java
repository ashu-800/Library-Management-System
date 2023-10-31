package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard prepareLibraryCard(){
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }
    public static LibraryCardResponse StudentToLibraryCardResponse(Student student){
        return LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getIssueDate())
                .build();
    }
}
