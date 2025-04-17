package edu.psu.sweng888.booknest;
import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private double price;
    private String imageUrl;

    // Firestore requires a no-argument constructor for deserialization
    public Book() {
        // Default constructor required for Firestore
    }

    // Parameterized constructor
    public Book(String title, String author, double price, String imageUrl) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
