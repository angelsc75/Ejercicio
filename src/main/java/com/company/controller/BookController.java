package com.company.controller;

import com.company.model.Book;
import com.company.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookRepository repo;

    @PostMapping("books")
    public Book create(@RequestBody() Book book) {
        return this.repo.save(book);
    }
    @GetMapping("books/{id}")
    public Book findById(@PathVariable Long id) {
        return this.repo.findById(id).orElseThrow();
    }
    @GetMapping("books")
    public List<Book> findAll(){
        return this.repo.findAll();
    }
    @PutMapping ("books/{id}")
    public Book update (@PathVariable Long id, @RequestBody Book book){
        if(this.repo.existsById(id))
            return this.repo.save(book);
        throw new NoSuchElementException();
    }
    @DeleteMapping("books/{id}")
    public void deleteById(@PathVariable Long id){
        this.repo.deleteById(id); 
    }
}