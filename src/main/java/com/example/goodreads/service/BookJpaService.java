package com.example.goodreads.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.goodreads.model.Book;
import com.example.goodreads.repository.BookRepository;
import com.example.goodreads.repository.BookJpaRepository;

@Service
public class BookJpaService implements BookRepository{

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public ArrayList<Book> getBooks(){
        List<Book> allBooks = bookJpaRepository.findAll();
        ArrayList<Book> book = new ArrayList<>(allBooks);
        return book;
    }

    @Override
    public Book getBookById(int bookId){
        try{
            Book book = bookJpaRepository.findById(bookId).get();
            return book;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Book addBook(Book book){
        // try{
            bookJpaRepository.save(book);
            return book;
        // }
    }

    @Override
    public Book updateBook(int bookId, Book book){
        try{
            Book newBook = bookJpaRepository.findById(bookId).get();
            if (book.getName() != null){
                newBook.setName(book.getName());
            }
            if (book.getImageUrl() != null){
                newBook.setImageUrl(book.getImageUrl());
            }
            bookJpaRepository.save(newBook);
            return newBook;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteBook(int bookId){
        try{
            bookJpaRepository.deleteById(bookId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}