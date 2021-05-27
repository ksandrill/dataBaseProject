package emris.control.tableInfo;

public class LibOrder {
    int id;
    int bookId;
    int readerId;
    String reader;
    String book;
    String status;

    public LibOrder(int id, int bookId, int readerId, String reader, String book, String status) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.reader = reader;
        this.book = book;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
