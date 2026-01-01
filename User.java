package finalprojectlibrary;

class User {

    private String name;
    private String email;
    private Book[] borrowedBooks;
    private int borrowedCount;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.borrowedBooks = new Book[3]; // Maximum 3 books
        this.borrowedCount = 0;
    }

    public String getEmail() {
        return email;
    }

    public boolean borrowBook(Book book) {
        if (borrowedCount < 3 && book.getAvailableCopies() > 0) {
            borrowedBooks[borrowedCount++] = book;
            book.borrowBook();
            return true;
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i].getTitle().equals(title)) {
                borrowedBooks[i].returnBook();
                borrowedBooks[i] = borrowedBooks[--borrowedCount];
                return true;
            }
        }
        return false;
    }
}
