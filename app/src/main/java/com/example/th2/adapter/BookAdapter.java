package com.example.th2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.databinding.ItemBookBinding;
import com.example.th2.listener.BookListener;
import com.example.th2.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    List<Book> books;
    int mode = 0;
    public BookAdapter(List<Book> books, BookListener listener) {
        this.books = books;
        this.listener = listener;
    }

    BookListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book book = books.get(position);
        holder.binding.bookName.setText(book.getName());
        holder.binding.bookInfo.setText(book.getAuthor() + "\n" + book.getPublisher() + "\n" + book.getRating());
        holder.binding.btnModify.setOnClickListener(v -> {
            listener.onModifyBook(book);
        });
        holder.binding.btnDelete.setOnClickListener(v -> {
            listener.onDeleteBook(book);
        });

        if(mode == 1){
            holder.binding.btnDelete.setVisibility(View.GONE);
            holder.binding.btnModify.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    public static class BookHolder extends RecyclerView.ViewHolder{
        ItemBookBinding binding;
        public BookHolder(@NonNull ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
