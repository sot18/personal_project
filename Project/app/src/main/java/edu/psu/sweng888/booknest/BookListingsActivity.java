package edu.psu.sweng888.booknest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;  // Ensure AppCompatActivity is correctly imported
import com.bumptech.glide.Glide;

public class BookListingsActivity extends AppCompatActivity {

    private ImageView imageViewBook;
    private TextView textViewTitle, textViewAuthor, textViewPrice;
    private Button buttonRent, buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listings);  // Use the updated layout

        // Initialize UI elements
        imageViewBook = findViewById(R.id.imageViewBook);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPrice = findViewById(R.id.textViewPrice);
        buttonRent = findViewById(R.id.buttonRent);
        buttonBuy = findViewById(R.id.buttonBuy);

        // Retrieve the passed Book object from the intent
        Book book = (Book) getIntent().getSerializableExtra("book");

        // If a Book object was passed
        if (book != null) {
            // Set book details to the respective views
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText("by " + book.getAuthor());
            textViewPrice.setText("$" + book.getPrice());

            // Load the book's cover image using Glide
            if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
                Glide.with(this)
                        .load(book.getImageUrl())
                        .into(imageViewBook);
            }
        }

        // Handle Rent button click (feature coming soon)
        buttonRent.setOnClickListener(v -> {
            Toast.makeText(this, "Renting Feature Coming Soon", Toast.LENGTH_SHORT).show();
        });

        // Handle Buy button click (feature coming soon)
        buttonBuy.setOnClickListener(v -> {
            Toast.makeText(this, "Buying Feature Coming Soon", Toast.LENGTH_SHORT).show();
        });
    }
}
