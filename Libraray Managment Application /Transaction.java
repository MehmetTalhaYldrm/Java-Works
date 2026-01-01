package finalprojectlibrary;

class Transaction {

    private User user;
    private Book book;
    private String type;
    private String date;

    public Transaction(User user, Book book, String type, String date) {
        this.user = user;
        this.book = book;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction: " + type + " | User: " + user.getEmail() + " | Book: " + book.getTitle() + " | Date: " + date;
    }
}
