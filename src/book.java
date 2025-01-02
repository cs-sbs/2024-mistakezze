import java.io.Serializable;


    class Book implements Serializable {
    private String title;
    private String author;
    private String ISBN;
    private String publishDate;
    private String category;  // 新增字段：图书类别

    // 构造方法：五个参数
    public Book(String title, String author, String ISBN, String publishDate, String category) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishDate = publishDate;
        this.category = category;
    }

    // Getter 和 Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // 显示图书信息
    public void displayBookInfo() {
        System.out.println("书名: " + title);
        System.out.println("作者: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("出版日期: " + publishDate);
        System.out.println("类别: " + category);
        System.out.println();
     }
    }
