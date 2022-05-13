package com.example.th2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.th2.databinding.ActivityAddBookBinding;
import com.example.th2.db.SQLiteHelper;
import com.example.th2.model.Book;

public class AddBookActivity extends AppCompatActivity {
    ActivityAddBookBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        ArrayAdapter albumAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.author));
        binding.bookAuthor.setAdapter(albumAdapter);


        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.publisher));
        binding.bookPublisher.setAdapter(categoryAdapter);

        binding.btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        binding.btnAdd.setOnClickListener(v -> {
            if(binding.bookName.getText().toString().isEmpty() || binding.bookResume.getText().toString().isEmpty()){
                Toast.makeText(this, "Missing infomation",Toast.LENGTH_LONG).show();
                return;
            }
            Book book = new Book();
            book.setName(binding.bookName.getText().toString());
            book.setResume(binding.bookResume.getText().toString());
            book.setAuthor(binding.bookAuthor.getSelectedItem().toString());
            book.setPublisher(binding.bookPublisher.getSelectedItem().toString());
            book.setRating(binding.bookRating.getRating());
            SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
            sqLiteHelper.addBook(book);
            setResult(Activity.RESULT_OK);
            finish();
        });
    }
}
