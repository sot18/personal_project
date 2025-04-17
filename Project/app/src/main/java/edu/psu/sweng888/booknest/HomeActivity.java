package edu.psu.sweng888.booknest;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private List<Book> bookList = new ArrayList<>();
    private FirebaseFirestore db;
    private Button buttonLogout;
    private EditText editTextSearch; // 🔍 Search bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();

        recyclerViewBooks = findViewById(R.id.recyclerViewBooks);
        recyclerViewBooks.setLayoutManager(new GridLayoutManager(this, 2));

        editTextSearch = findViewById(R.id.editTextSearch); // 🔍

        FloatingActionButton fabAddBook = findViewById(R.id.fabAddBook);
        fabAddBook.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Opening Add Book", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, AddBookActivity.class));
        });

        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        bookAdapter = new BookAdapter(bookList, book -> {
            Intent intent = new Intent(HomeActivity.this, BookListingsActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        });

        recyclerViewBooks.setAdapter(bookAdapter);

        // 🔍 Real-time search listener
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bookAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        fetchBooks();
    }

    private void fetchBooks() {
        db.collection("books").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    bookList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Book book = document.toObject(Book.class);
                        bookList.add(book);
                    }
                    bookAdapter.notifyDataSetChanged();
                    bookAdapter.updateFullList(bookList); // 🔁 Update backup list for filtering
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching books", e));
    }
}
