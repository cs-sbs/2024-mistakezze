import java.sql.*;

class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBookToDatabase(Book book) {
        String query = "INSERT INTO books (title, author, ISBN, publishDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getISBN());
            ps.setString(4, book.getPublishDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookFromDatabase(String ISBN) {
        String query = "DELETE FROM books WHERE ISBN = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, ISBN);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBooksFromDatabase() {
        String query = "SELECT * FROM books";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
