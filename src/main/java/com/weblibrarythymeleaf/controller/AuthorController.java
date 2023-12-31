package com.weblibrarythymeleaf.controller;

import com.weblibrarythymeleaf.entity.Author;
import com.weblibrarythymeleaf.service.AuthorService;
import com.weblibrarythymeleaf.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@Controller
@RequestMapping("/author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorController {
    public static final Logger logger = (Logger) LoggerFactory.getLogger(AuthorController.class);
    private BookService bookService;
    private AuthorService authorService;

    public AuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public String listAuthors(Model theModel){
        //get author from db
        List<Author> theAuthors = authorService.findAll();
        //add to the spring model
        theModel.addAttribute("authors",theAuthors);
        return "list-authors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind form data
        Author theAuthor = new Author();
        theModel.addAttribute("authors",theAuthor);
        return "author-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("authorId") int theID, Model theModel){
        //get the author from the service
        Author theAuthor = authorService.findById(theID);
        //set author as a model attribute to pre-populate the form
        theModel.addAttribute("authors",theAuthor);
        return "author-form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("authors") Author theAuthor){
        //save the author
        authorService.save(theAuthor);
        //use a redirect to prevent duplicate submissions
        return "redirect/author/list";
    }

    public String delete(@RequestParam("authorId") int theId){
        //delete the author
        authorService.deleteById(theId);
        //redirect to the authors list
        return "redirect/author/list";
    }
}
