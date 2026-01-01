package finalprojectlibrary;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Add a New Book");
            System.out.println("2. Register a New User");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Display all Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Total Copies: ");
                    int copies = scanner.nextInt();
                    library.addBook(title, author, copies);
                    break;
                case 2:
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter User Email: ");
                    String email = scanner.nextLine();
                    library.registerUser(name, email);
                    break;
                case 3:
                    System.out.print("Enter User Email: ");
                    String userEmail = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String bookAuthor = scanner.nextLine();
                    library.borrowBook(userEmail, bookTitle, bookAuthor);
                    break;
                case 4:
                    System.out.print("Enter User Email: ");
                    String returnEmail = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnEmail, returnTitle);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Program exited successfully.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
