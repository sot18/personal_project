package edu.psu.sweng888.booknest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {

    private List<Book> bookList;
    private List<Book> bookListFull;
    private OnBookClickListener onBookClickListener;

    // Listener interface
    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    // Constructor
    public BookAdapter(List<Book> bookList, OnBookClickListener listener) {
        this.bookList = bookList;
        this.bookListFull = new ArrayList<>(bookList);  // Clone for filtering
        this.onBookClickListener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.textTitle.setText(book.getTitle());

        if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(book.getImageUrl())
                    .into(holder.imageViewBookCover);
        } else {
            holder.imageViewBookCover.setImageResource(R.drawable.placeholder_image);
        }

        holder.itemView.setOnClickListener(v -> {
            if (onBookClickListener != null) {
                onBookClickListener.onBookClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imageViewBookCover;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewBookCover = itemView.findViewById(R.id.imageViewBookCover);
        }
    }

    // 🔍 Filterable implementation
    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    private final Filter bookFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Book> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(bookListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Book book : bookListFull) {
                    if (book.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(book);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookList.clear();
            bookList.addAll((List<Book>) results.values);
            notifyDataSetChanged();
        }
    };

    // 🛠️ Optional: Call this if the original list changes (e.g., after fetch)
    public void updateFullList(List<Book> newList) {
        bookListFull = new ArrayList<>(newList);
    }
}
