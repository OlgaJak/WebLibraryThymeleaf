package com.weblibrarythymeleaf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {

    @GeneratedValue
    @Column(name = "ID")
    @Id
    private int id;
    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    List<Book> booksList=new ArrayList<>();

    public void add(Book tempBook){
        if (booksList==null){
            booksList=new ArrayList<>();
        }
        // this is bi-directional relationship
        booksList.add(tempBook);
        tempBook.setAuthor(this);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }
}
