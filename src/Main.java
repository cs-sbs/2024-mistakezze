import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        FileManager fileManager = new FileManager();

        // 加载备份数据
        List<Book> loadedBooks = fileManager.loadBooksFromFile();
        for (Book book : loadedBooks) {
            bookManager.addBook(book);
        }

        // 启动备份线程
        BackupThread backupThread = new BackupThread(bookManager, fileManager);
        backupThread.start();

        // 初始化 UserManager
        UserManager userManager = new UserManager();

        // 注册一些默认用户（管理员和普通用户）
        userManager.registerUser("admin", "admin123", "admin");
        userManager.registerUser("user1", "password", "user");

        boolean isLoggedIn = false;
        User currentUser = null;

        while (true) {
            System.out.println("欢迎来到图书管理系统");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 浏览图书");
            System.out.println("4. 搜寻图书");
            System.out.println("5. 添加图书 (需管理员权限)");
            System.out.println("6. 删除图书 (需管理员权限)");
            System.out.println("7. 登出");
            System.out.println("8. 退出");
            System.out.print("请输入你的选择:");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // 注册用户
                    System.out.print("请输入用户名: ");
                    String username = scanner.nextLine();
                    System.out.print("请输入密码: ");
                    String password = scanner.nextLine();
                    System.out.print("请输入你的身份 (admin/user): ");
                    String role = scanner.nextLine();
                    userManager.registerUser(username, password, role);
                    break;
                case 2:
                    // 登录
                    System.out.print("请输入用户名: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("请输入密码: ");
                    String loginPassword = scanner.nextLine();

                    currentUser = userManager.loginUser(loginUsername, loginPassword);
                    if (currentUser != null) {
                        System.out.println("登录成功, 欢迎使用图书管理系统 " + currentUser.getUsername() + "!");
                        isLoggedIn = true;
                    } else {
                        System.out.println("无效的用户名或密码。请重试.");
                    }
                    break;
                case 3:  // 浏览所有书籍
                    if (!isLoggedIn) {
                        System.out.println("请先登录.");
                    } else {
                        // 显示所有书籍或按类别查看书籍
                        System.out.println("1. 查看所有书籍");
                        System.out.println("2. 通过分类查照书籍");
                        System.out.print("输入你的选择: ");
                        int viewChoice = scanner.nextInt();
                        scanner.nextLine();  // consume newline

                        if (viewChoice == 1) {
                            // 查看所有图书
                            for (Book book : bookManager.getAllBooks()) {
                                book.displayBookInfo();
                            }
                        } else if (viewChoice == 2) {
                            // 按类别查看图书
                            System.out.print("输入类别（例如，计算机、医学、文学）: ");
                            String category = scanner.nextLine();
                            List<Book> booksByCategory = bookManager.getBooksByCategory(category);
                            if (booksByCategory.isEmpty()) {
                                System.out.println("此类别中没有图书.");
                            } else {
                                for (Book book : booksByCategory) {
                                    book.displayBookInfo();
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    // 查找书籍
                    System.out.print("输入要搜索的书名或ISBN: ");
                    String keyword = scanner.nextLine();
                    Book foundBook = bookManager.searchBook(keyword);
                    if (foundBook != null) {
                        foundBook.displayBookInfo();
                    } else {
                        System.out.println("对不起，无法找到该图书.");
                    }
                    break;
                case 5:
                    // 添加书籍（仅管理员权限）
                    if (!isLoggedIn || !currentUser.getRole().equals("admin")) {
                        System.out.println("只有管理员才能添加书籍.");
                        break;
                    }

                    System.out.print("请输入要添加书名: ");
                    String title = scanner.nextLine();
                    System.out.print("请输入作者名: ");
                    String author = scanner.nextLine();
                    System.out.print("请输入ISBN: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("请输入出版日期(YYYY-MM-DD): ");
                    String publishDate = scanner.nextLine();
                    System.out.print("请输入类别: ");
                    String category = scanner.nextLine();

                    Book newBook = new Book(title, author, ISBN, publishDate, category);
                    bookManager.addBook(newBook);
                    break;
                case 6:
                    // 删除书籍（仅管理员权限）
                    if (!isLoggedIn || !currentUser.getRole().equals("admin")) {
                        System.out.println("只有管理员才能删除书籍.");
                        break;
                    }

                    System.out.print("输入要删除的图书的ISBN: ");
                    String isbnToDelete = scanner.nextLine();
                    bookManager.deleteBook(isbnToDelete);
                    break;
                case 7:
                    // 登出
                    if (isLoggedIn) {
                        isLoggedIn = false;
                        currentUser = null;
                        System.out.println("你已成功登出.");
                    } else {
                        System.out.println("你尚未登录.");
                    }
                    break;
                case 8:
                    // 保存数据并退出
                    fileManager.saveBooksToFile(bookManager.getAllBooks());
                    System.out.println("数据已保存。程序退出.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效命令，请再试一次.");
            }
            // 每个操作完成后添加返回主界面的选择
            System.out.println("\n按任意键返回主界面...");
            scanner.nextLine();  // 等待用户输入
        }
    }
}
