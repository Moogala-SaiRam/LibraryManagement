import java.util.*;

public class BookServiceClient {
    public static void main(String[] args) {
        BookService service = BookServiceImpl.getInstance();
        service.addBook(new Book("Book1", "Author1", 2021));
        service.addBook(new Book("Book2", "Author1", 2022));
        service.addBook(new Book("Book3", "Author2", 2021));
        service.addBook(new Book("Book4", "Author3", 2021));
        service.addBook(new Book("Book5", "Author3", 2023));

        System.out.println("************** All Books ***************");
        for (Book b : service.findAllBooks()) {
            System.out.println(b);
        }

        System.out.println("************** Sorted by Year ***************");
        List<Book> sortedByYear = service.sortBooks("year");
        for (Book b : sortedByYear) {
            System.out.println(b);
        }

        System.out.println("************** Borrow a Book ***************");
        service.borrowBook("Book1", "John Doe");
        List<Book> borrowedBooks = service.findBooksByBorrower("John Doe");
        for (Book b : borrowedBooks) {
            System.out.println(b);
        }

        System.out.println("************** Export Books to File ***************");
        if (service.exportBooksToFile("books.txt")) {
            System.out.println("Books exported successfully.");
        } else {
            System.out.println("Export failed.");
        }
    }
}
