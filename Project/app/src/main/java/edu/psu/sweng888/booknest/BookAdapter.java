package edu.psu.sweng888.booknest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private OnBookClickListener onBookClickListener;

    // Define an interface for handling book clicks
    public interface OnBookClickListener {
        void onBookClick(Book book);  // When a book is clicked
    }

    // Constructor: Accept List of Books and OnBookClickListener
    public BookAdapter(List<Book> bookList, OnBookClickListener listener) {
        this.bookList = bookList;
        this.onBookClickListener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Get the book at the current position
        Book book = bookList.get(position);

        // Set the book title to the text view
        if (holder.textTitle != null) {
            holder.textTitle.setText(book.getTitle());
        }

        // Load book cover image safely with Glide
        if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(book.getImageUrl())
                    .into(holder.imageViewBookCover);
        } else {
            // Use a placeholder image if image URL is missing
            holder.imageViewBookCover.setImageResource(R.drawable.placeholder_image);
        }

        // Set up the item click listener for the book
        holder.itemView.setOnClickListener(v -> {
            // Check if listener is not null and then trigger the click event
            if (onBookClickListener != null) {
                onBookClickListener.onBookClick(book);  // Pass the clicked book object
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of books in the list
        return bookList.size();
    }

    // ViewHolder for the individual book items
    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imageViewBookCover;

        // Initialize the views for each item
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewBookCover = itemView.findViewById(R.id.imageViewBookCover);
        }
    }
}
