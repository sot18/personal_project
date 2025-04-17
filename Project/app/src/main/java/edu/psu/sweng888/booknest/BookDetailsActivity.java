package edu.psu.sweng888.booknest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView textTitle, textAuthor, textPrice;
    private ImageView imageViewBookCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // Initialize views
        textTitle = findViewById(R.id.textViewTitle);
        textAuthor = findViewById(R.id.textViewAuthor);
        textPrice = findViewById(R.id.textViewPrice);
        imageViewBookCover = findViewById(R.id.imageViewBookCover);

        // Get the book object passed from the previous activity
        Book book = (Book) getIntent().getSerializableExtra("book");

        if (book != null) {
            // Set the book details to the views
            textTitle.setText(book.getTitle());
            textAuthor.setText("by " + book.getAuthor());
            textPrice.setText("$" + book.getPrice());

            // Load the book cover image using Glide (if available)
            if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
                Glide.with(this)
                        .load(book.getImageUrl())
                        .into(imageViewBookCover);
            } else {
                // Set a placeholder image if the book doesn't have an image URL
                imageViewBookCover.setImageResource(R.drawable.placeholder_image);
            }
        }
    }
}
