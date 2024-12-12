import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileManager {
    private static final String FILE_PATH = "books_backup.dat";

    // 保存图书到文件
    public void saveBooksToFile(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件加载图书数据
    public List<Book> loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // 如果没有找到文件或发生错误，返回一个空的列表
        }
    }
}
