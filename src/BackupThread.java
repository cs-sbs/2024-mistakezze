class BackupThread extends Thread {
    private BookManager bookManager;
    private FileManager fileManager;

    public BackupThread(BookManager bookManager, FileManager fileManager) {
        this.bookManager = bookManager;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000); // 每分钟备份一次
                fileManager.saveBooksToFile(bookManager.getAllBooks());
                System.out.println("Backup completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
