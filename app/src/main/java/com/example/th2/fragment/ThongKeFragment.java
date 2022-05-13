package com.example.th2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.th2.adapter.BookAdapter;
import com.example.th2.databinding.FragmentThongkeBinding;
import com.example.th2.db.SQLiteHelper;
import com.example.th2.model.Book;

import java.util.List;

public class ThongKeFragment extends Fragment {
    FragmentThongkeBinding binding;
    SQLiteHelper sqLiteHelper;
    BookAdapter bookAdapter;
    List<Book> books;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentThongkeBinding.inflate(inflater,container,false);
        initView();


        return binding.getRoot();
    }

    private void initView() {
        sqLiteHelper = new SQLiteHelper(requireContext());
        books = sqLiteHelper.getAllBookFromDB();
        bookAdapter = new BookAdapter(books,null);
        bookAdapter.setMode(1);
        binding.books.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        binding.books.setAdapter(bookAdapter);


        binding.searchName.setOnClickListener(v -> {
            books = sqLiteHelper.getBookByName(binding.name.getText().toString());
            bookAdapter.setBooks(books);
        });

        binding.searchAuthor.setOnClickListener(v -> {
            books = sqLiteHelper.getBookByAuthor(binding.author.getText().toString());
            bookAdapter.setBooks(books);
        });
    }
}
