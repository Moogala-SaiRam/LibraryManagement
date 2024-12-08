import java.util.List;

public interface BookService {
    boolean addBook(Book b);
    boolean updateBook(Book b);
    boolean deleteBook(String title);
    boolean deleteBooksByYear(int year);
    boolean deleteBooksByAuthor(String author);
    List<Book> findAllBooks();
    Book findBookByTitle(String title);
    List<Book> findBooksByYear(int year);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByPartialTitle(String partialTitle);
    List<Book> sortBooks(String criteria); // Criteria: "title", "author", "year"
    boolean exportBooksToFile(String filePath);
    boolean borrowBook(String title, String borrowerName);
    boolean returnBook(String title);
    List<Book> findBooksByBorrower(String borrowerName);
}

