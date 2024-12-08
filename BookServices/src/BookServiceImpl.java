import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;
    private final List<Book> books = new ArrayList<>();
    private final Map<String, String> borrowedBooks = new HashMap<>();

    private BookServiceImpl() {}

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean addBook(Book b) {
        return books.add(b);
    }

    @Override
    public boolean updateBook(Book b) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(b.getTitle())) {
                books.set(i, b);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBook(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public boolean deleteBooksByYear(int year) {
        return books.removeIf(book -> book.getYear() == year);
    }

    @Override
    public boolean deleteBooksByAuthor(String author) {
        return books.removeIf(book -> book.getAuthor().equalsIgnoreCase(author));
    }

    @Override
    public List<Book> findAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findBooksByPartialTitle(String partialTitle) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortBooks(String criteria) {
        List<Book> sortedBooks = new ArrayList<>(books);
        switch (criteria.toLowerCase()) {
            case "title":
                sortedBooks.sort(Comparator.comparing(Book::getTitle));
                break;
            case "author":
                sortedBooks.sort(Comparator.comparing(Book::getAuthor));
                break;
            case "year":
                sortedBooks.sort(Comparator.comparingInt(Book::getYear));
                break;
        }
        return sortedBooks;
    }

    @Override
    public boolean exportBooksToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Book book : books) {
                writer.write(book.toString() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean borrowBook(String title, String borrowerName) {
        Book book = findBookByTitle(title);
        if (book != null && !borrowedBooks.containsKey(title)) {
            borrowedBooks.put(title, borrowerName);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String title) {
        return borrowedBooks.remove(title) != null;
    }

    @Override
    public List<Book> findBooksByBorrower(String borrowerName) {
        List<Book> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : borrowedBooks.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(borrowerName)) {
                result.add(findBookByTitle(entry.getKey()));
            }
        }
        return result;
    }
}
