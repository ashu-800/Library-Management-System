package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.model.LibraryCard;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {

    String name;

    String email;

    LibraryCardResponse libraryCardReponse;
}

