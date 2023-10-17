package com.example.librarymanagementsystem.contoller;

import com.example.librarymanagementsystem.service.Impl.StudentService;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response=studentService.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int id){
        Student response=studentService.getStudent(id);
        if(response!=null){
            return new ResponseEntity(response,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid id!", HttpStatus.BAD_REQUEST);
    }
}
