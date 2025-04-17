package edu.psu.sweng888.booknest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBookActivity extends AppCompatActivity {
    private EditText editTitle, editAuthor, editPrice, editImageUrl;
    private Button buttonSave;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);  // Ensure correct layout file

        db = FirebaseFirestore.getInstance();

        // Initialize EditText fields
        editTitle = findViewById(R.id.editTextTitle);
        editAuthor = findViewById(R.id.editTextAuthor);
        editPrice = findViewById(R.id.editTextPrice);
        editImageUrl = findViewById(R.id.editTextImageUrl);  // Field for Image URL
        buttonSave = findViewById(R.id.buttonAddBook);

        buttonSave.setOnClickListener(v -> {
            // Get values from EditText fields
            String title = editTitle.getText().toString();
            String author = editAuthor.getText().toString();
            String priceText = editPrice.getText().toString();
            String imageUrl = editImageUrl.getText().toString();  // Get Image URL

            // Validate fields
            if (title.isEmpty() || author.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert price to double
            double price = Double.parseDouble(priceText);

            // Create a new Book object with optional image URL
            Book book = new Book(title, author, price, imageUrl.isEmpty() ? null : imageUrl);  // If no image URL, set to null

            // Add the book to Firestore
            db.collection("books").add(book)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Book Added!", Toast.LENGTH_SHORT).show();
                        finish();  // Close the activity after successfully adding the book
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error adding book", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
