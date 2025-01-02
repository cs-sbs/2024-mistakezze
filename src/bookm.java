import java.util.ArrayList;
import java.util.List;

class BookManager {
    private List<Book> books = new ArrayList<>();

    // 添加图书
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // 删除图书
    public void deleteBook(String ISBN) {
        boolean removed = books.removeIf(book -> book.getISBN().equals(ISBN));
        if (removed) {
            System.out.println("Book with ISBN " + ISBN + " deleted.");
        } else {
            System.out.println("No book found with ISBN " + ISBN);
        }
    }

    // 查找图书
    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getISBN().equals(keyword)) {
                return book;
            }
        }
        return null;
    }

    // 按类别查询图书
    public List<Book> getBooksByCategory(String category) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    // 获取所有图书
    public List<Book> getAllBooks() {
        return books;
    }
}
