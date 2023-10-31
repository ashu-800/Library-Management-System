package com.example.librarymanagementsystem.dto.requestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorRequest {

    String name;

    int age;

    String email;
}




