package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByGenre(Genre genre);

    @Query(value = "select b from Book b where b.genre=:genre and b.cost>:x")
    List<Book> findBookByGenreAndCostMoreThanX(Genre genre, double x);

    @Query(value = "select b from Book b where b.noOfPages>:a and b.noOfPages<:b")
    List<Book> findBooksHavingPagesBetweenAAndB(int a, int b);

    @Query(value = "select distinct b.author from Book b where b.genre=:genre")
    List<Author> findAllAuthorsWithGenreX(Genre genre);

    @Query(value = "select * from book where genre = :genre and cost > :cost",nativeQuery = true)
    List<Book> getBooksByGenreAndCostGreaterThan(String genre, double cost);

    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")
    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);
}
