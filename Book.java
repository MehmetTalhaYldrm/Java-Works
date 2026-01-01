package finalprojectlibrary;

import java.util.Scanner;

class Book {

    private String title;
    private String author;
    private int availableCopies;
    private int totalCopies;

    public Book(String title, String author, int totalCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = totalCopies;
        this.totalCopies = totalCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    public void addCopies(int copies) {
        availableCopies += copies;
        totalCopies += copies;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + availableCopies + " copies available.";
    }
}
