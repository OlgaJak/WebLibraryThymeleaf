package com.weblibrarythymeleaf.repository;

import com.weblibrarythymeleaf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("Select b from Book b where b.book_name LIKE %?1%"
            +"OR b.books_author LIKE %?1%")
    List<Book> findByName(String keyword);
}
