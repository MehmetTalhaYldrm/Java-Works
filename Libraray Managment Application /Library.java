package finalprojectlibrary;

class Library {

    private Book[][] shelves;
    private User[] users;
    private Transaction[] transactions;
    private int userCount;
    private int transactionCount;
    private static final int SHELF_CAPACITY = 4;

    public Library() {
        shelves = new Book[10][SHELF_CAPACITY]; // Start with 10 shelves
        users = new User[100]; // Maximum 100 users
        transactions = new Transaction[1000]; // Maximum 1000 transactions
        userCount = 0;
        transactionCount = 0;
    }

    public void addBook(String title, String author, int copies) {
        for (int i = 0; i < shelves.length; i++) {
            for (int j = 0; j < SHELF_CAPACITY; j++) {
                if (shelves[i][j] != null && shelves[i][j].getTitle().equals(title) && shelves[i][j].getAuthor().equals(author)) {
                    shelves[i][j].addCopies(copies);
                    System.out.println("Added copies to existing book.");
                    return;
                }
            }
        }

        // Add new book
        for (int i = 0; i < shelves.length; i++) {
            for (int j = 0; j < SHELF_CAPACITY; j++) {
                if (shelves[i][j] == null) {
                    shelves[i][j] = new Book(title, author, copies);
                    System.out.println("Added new book to library.");
                    return;
                }
            }
        }

        // If no space, expand shelves
        expandShelves();
        addBook(title, author, copies);
    }

    private void expandShelves() {
        Book[][] newShelves = new Book[shelves.length + 1][SHELF_CAPACITY];
        for (int i = 0; i < shelves.length; i++) {
            newShelves[i] = shelves[i];
        }
        shelves = newShelves;
        System.out.println("Expanded shelves to accommodate more books.");
    }

    public void registerUser(String name, String email) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getEmail().equals(email)) {
                System.out.println("User with this email already exists.");
                return;
            }
        }
        users[userCount++] = new User(name, email);
        System.out.println("User registered successfully.");
    }

    public User findUserByEmail(String email) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }
        }
        return null;
    }

    public Book findBookByTitleAndAuthor(String title, String author) {
        for (Book[] shelf : shelves) {
            for (Book book : shelf) {
                if (book != null && book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                    return book;
                }
            }
        }
        return null;
    }

    public void borrowBook(String email, String title, String author) {
        User user = findUserByEmail(email);
        Book book = findBookByTitleAndAuthor(title, author);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (user.borrowBook(book)) {
            transactions[transactionCount++] = new Transaction(user, book, "Borrow", java.time.LocalDate.now().toString());
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Unable to borrow book. Either limit reached or no copies available.");
        }
    }

    public void returnBook(String email, String title) {
        User user = findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        if (user.returnBook(title)) {
            Book book = findBookByTitleAndAuthor(title, "");
            transactions[transactionCount++] = new Transaction(user, book, "Return", java.time.LocalDate.now().toString());
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found in user's borrowed list.");
        }
    }

    public void displayBooks() {
        for (Book[] shelf : shelves) {
            for (Book book : shelf) {
                if (book != null) {
                    System.out.println(book);
                }
            }
        }
    }
}
