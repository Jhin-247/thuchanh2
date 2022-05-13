package com.example.th2.listener;

import com.example.th2.model.Book;

public interface BookListener {
    void onDeleteBook(Book book);
    void onModifyBook(Book book);
}
