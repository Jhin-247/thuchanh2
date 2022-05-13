package com.example.th2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.th2.databinding.ActivityModifyBookBinding;
import com.example.th2.db.SQLiteHelper;
import com.example.th2.model.Book;

public class ModifyBookActivity extends AppCompatActivity {
    ActivityModifyBookBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModifyBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        String name = intent.getExtras().getString("name");
        String author = intent.getExtras().getString("author");
        String resume = intent.getExtras().getString("resume");
        String publisher = intent.getExtras().getString("publisher");
        float rating = intent.getExtras().getFloat("rating");

        ArrayAdapter albumAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.author));
        binding.bookAuthor.setAdapter(albumAdapter);


        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.publisher));
        binding.bookPublisher.setAdapter(categoryAdapter);



        binding.bookName.setText(name);
        binding.bookResume.setText(resume);
        binding.bookRating.setRating(rating);
        switch (author){
            case "Nguyễn Mạnh Sơn":
                binding.bookAuthor.setSelection(0);
                break;
            case "Trần Đình Quế":
                binding.bookAuthor.setSelection(1);
                break;
            case "Nguyễn Duy Phương":
                binding.bookAuthor.setSelection(2);
                break;
            case "Phạm Thế Quế":
                binding.bookAuthor.setSelection(3);
                break;
            case "Nguyễn Quang Hoan":
                binding.bookAuthor.setSelection(4);
                break;
            case "Phan Thị Hà":
                binding.bookAuthor.setSelection(5);
                break;
        }

        switch (publisher){
            case "Khoa học và xã hội":
                binding.bookPublisher.setSelection(0);
                break;
            case "Bưu điện":
                binding.bookPublisher.setSelection(1);
                break;
            case "Khoa học và kỹ thuật":
                binding.bookPublisher.setSelection(2);
                break;
            case "Thống kê":
                binding.bookPublisher.setSelection(3);
                break;
        }

        binding.btnModify.setOnClickListener(v -> {
            if(binding.bookName.getText().toString().isEmpty() || binding.bookResume.getText().toString().isEmpty()){
                Toast.makeText(this, "Missing infomation",Toast.LENGTH_LONG).show();
                return;
            }
            Book book = new Book();
            book.setId(id);
            book.setName(binding.bookName.getText().toString());
            book.setResume(binding.bookResume.getText().toString());
            book.setAuthor(binding.bookAuthor.getSelectedItem().toString());
            book.setPublisher(binding.bookPublisher.getSelectedItem().toString());
            book.setRating(binding.bookRating.getRating());
            SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
            sqLiteHelper.updateBook(book);
            setResult(Activity.RESULT_OK);
            finish();
        });
    }
}
