package com.example.th2.db;

import static com.example.th2.Constants.DB_CONSTANT.BOOK_DB;
import static com.example.th2.Constants.DB_CONSTANT.BOOK_TABLE;
import static com.example.th2.Constants.DB_CONSTANT.DB_VERSION;
import static com.example.th2.model.Book.BOOK_AUTHOR;
import static com.example.th2.model.Book.BOOK_ID;
import static com.example.th2.model.Book.BOOK_NAME;
import static com.example.th2.model.Book.BOOK_PUBLISHER;
import static com.example.th2.model.Book.BOOK_RATING;
import static com.example.th2.model.Book.BOOK_RESUME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context) {
        super(context, BOOK_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createBookDB = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT ,%s TEXT ,%s TEXT ,%s TEXT ,%s REAL)", BOOK_TABLE, BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_RESUME, BOOK_PUBLISHER, BOOK_RATING);
        sqLiteDatabase.execSQL(createBookDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    public void test() {
//        Cursor cursor = getReadableDatabase().query(TABLE_NAME, new String[]{ID, NAME, SINGER, ALBUM, CATEGORY, ISLIKE, AVATAR_PATH, "COUNT(*) as count"}, null, null, CATEGORY, null, "count DESC");
//        while (cursor.moveToNext()) {
//            Log.d("123123", "0: " + cursor.getInt(0));
//            Log.d("123123", "1: " + cursor.getString(1));
//            Log.d("123123", "2: " + cursor.getString(2));
//            Log.d("123123", "3: " + cursor.getString(3));
//            Log.d("123123", "4: " + cursor.getString(4));
//            Log.d("123123", "5: " + cursor.getInt(5));
//            Log.d("123123", "6: " + cursor.getString(6));
//            Log.d("123123", "7: " + cursor.getInt(7));
//        }
//    }

    public void addBook(Book book) {
        //BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_RESUME, BOOK_PUBLISHER, BOOK_RATING
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_NAME, book.getName());
        contentValues.put(BOOK_AUTHOR, book.getAuthor());
        contentValues.put(BOOK_RESUME, book.getResume());
        contentValues.put(BOOK_PUBLISHER, book.getPublisher());
        contentValues.put(BOOK_RATING, book.getRating());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(BOOK_TABLE, null, contentValues);
    }

    public List<Book> getAllBookFromDB() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(BOOK_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
//            Log.d("123123", "0: " + cursor.getInt(0));
//            Log.d("123123", "1: " + cursor.getString(1));
//            Log.d("123123", "2: " + cursor.getString(2));
//            Log.d("123123", "3: " + cursor.getString(3));
//            Log.d("123123", "4: " + cursor.getString(4));
//            Log.d("123123", "5: " + cursor.getInt(5));
//            Log.d("123123", "6: " + cursor.getString(6));
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setResume(cursor.getString(3));
            book.setPublisher(cursor.getString(4));
            book.setRating(cursor.getFloat(5));
            books.add(book);
        }

        cursor.close();
        return books;
    }

    public void deleteBook(Book book) {
        String whereClause = BOOK_ID + "=?";
        String[] whereArgs = {Integer.toString(book.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(BOOK_TABLE, whereClause, whereArgs);
    }

    public void updateBook(Book book) {
        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID, book.getId());
        cv.put(BOOK_NAME, book.getName());
        cv.put(BOOK_AUTHOR, book.getAuthor());
        cv.put(BOOK_PUBLISHER, book.getPublisher());
        cv.put(BOOK_RESUME, book.getResume());
        cv.put(BOOK_RATING, book.getRating());
//        Log.d("123123","" + book.getId());
//        Log.d("123123","" + book.getName());
//        Log.d("123123","" + book.getAuthor());
//        Log.d("123123","" + book.getPublisher());
//        Log.d("123123","" + book.getResume());
//        Log.d("123123","" + book.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(BOOK_TABLE, cv, BOOK_ID + "=?", new String[]{Integer.toString(book.getId())});
    }

    public List<Book> getBookByName(String name) {
        List<Book> books = new ArrayList<>();
        String whereClause = BOOK_NAME + " like ?";
        String[] whereArgs = {"%" + name + "%"};
        Cursor cursor = getReadableDatabase().query(BOOK_TABLE, null, whereClause, whereArgs, null, null, BOOK_RATING);
        while (cursor.moveToNext()) {
//            Log.d("123123", "0: " + cursor.getInt(0));
//            Log.d("123123", "1: " + cursor.getString(1));
//            Log.d("123123", "2: " + cursor.getString(2));
//            Log.d("123123", "3: " + cursor.getString(3));
//            Log.d("123123", "4: " + cursor.getString(4));
//            Log.d("123123", "5: " + cursor.getInt(5));
//            Log.d("123123", "6: " + cursor.getString(6));
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setResume(cursor.getString(3));
            book.setPublisher(cursor.getString(4));
            book.setRating(cursor.getFloat(5));
            books.add(book);
        }

        cursor.close();


        return books;
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String whereClause = BOOK_AUTHOR + " like ?";
        String[] whereArgs = {"%" + author + "%"};
        Cursor cursor = getReadableDatabase().query(BOOK_TABLE, null, whereClause, whereArgs, null, null, BOOK_RATING);
        while (cursor.moveToNext()) {
//            Log.d("123123", "0: " + cursor.getInt(0));
//            Log.d("123123", "1: " + cursor.getString(1));
//            Log.d("123123", "2: " + cursor.getString(2));
//            Log.d("123123", "3: " + cursor.getString(3));
//            Log.d("123123", "4: " + cursor.getString(4));
//            Log.d("123123", "5: " + cursor.getInt(5));
//            Log.d("123123", "6: " + cursor.getString(6));
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setResume(cursor.getString(3));
            book.setPublisher(cursor.getString(4));
            book.setRating(cursor.getFloat(5));
            books.add(book);
        }

        cursor.close();


        return books;
    }


    public List<Book> thongke() {
        List<Book> books = new ArrayList<>();

        Cursor cursor = getReadableDatabase().query(BOOK_TABLE, new String[]{BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_RESUME, BOOK_PUBLISHER, BOOK_RATING, "COUNT(*) as count"}, null, null, BOOK_PUBLISHER, null, BOOK_RATING);
        while (cursor.moveToNext()) {
//            Log.d("123123", "0: " + cursor.getInt(0));
//            Log.d("123123", "1: " + cursor.getString(1));
//            Log.d("123123", "2: " + cursor.getString(2));
//            Log.d("123123", "3: " + cursor.getString(3));
//            Log.d("123123", "4: " + cursor.getString(4));
//            Log.d("123123", "5: " + cursor.getInt(5));
//            Log.d("123123", "6: " + cursor.getString(6));
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setResume(cursor.getString(3));
            book.setPublisher(cursor.getString(4));
            book.setRating(cursor.getFloat(5));
            books.add(book);
        }

        cursor.close();


        return books;
    }

}
