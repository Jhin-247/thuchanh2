package com.example.th2.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.ModifyBookActivity;
import com.example.th2.adapter.BookAdapter;
import com.example.th2.databinding.FragmentDanhSachBinding;
import com.example.th2.db.SQLiteHelper;
import com.example.th2.listener.BookListener;
import com.example.th2.model.Book;

import java.util.List;

public class DanhsachFragment extends Fragment {
    FragmentDanhSachBinding binding;
    BookAdapter bookAdapter;
    List<Book> books;
    SQLiteHelper sqLiteHelper;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    resetData();
                }
            }
    );


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initView() {
        sqLiteHelper = new SQLiteHelper(requireContext());

        books = sqLiteHelper.getAllBookFromDB();
        bookAdapter = new BookAdapter(books, new BookListener() {
            @Override
            public void onDeleteBook(Book book) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setTitle("Co chac muon xoa?");
                builder.setPositiveButton("Ok", (dialogInterface, i) -> {
                    sqLiteHelper.deleteBook(book);
                    resetData();
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onModifyBook(Book book) {
                Intent intent = new Intent(requireActivity(), ModifyBookActivity.class);
                intent.putExtra("id",book.getId());
                intent.putExtra("name",book.getName());
                intent.putExtra("author",book.getAuthor());
                intent.putExtra("resume",book.getResume());
                intent.putExtra("publisher",book.getPublisher());
                intent.putExtra("rating",book.getRating());
                resultLauncher.launch(intent);
            }
        });

        binding.songs.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.songs.setAdapter(bookAdapter);


    }

    public void resetData() {
        books = sqLiteHelper.getAllBookFromDB();
        bookAdapter.setBooks(books);

//        sqLiteHelper.test();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDanhSachBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }
}
