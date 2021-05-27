package emris.control.tableInfo;

import java.sql.Date;

public class Binfo {
    int id;
    int librarian_id;
    int place_id;
    Date whenTake;
    Date whenShouldReturn;
    Date whenReturn;
    String librarian;
    String bookName;

    public Binfo(int id, int librarian_id, int place_id, Date whenTake, Date whenShouldReturn, Date whenReturn, String librarian, String bookName) {
        this.id = id;
        this.librarian_id = librarian_id;
        this.place_id = place_id;
        this.whenTake = whenTake;
        this.whenShouldReturn = whenShouldReturn;
        this.whenReturn = whenReturn;
        this.librarian = librarian;
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLibrarian_id() {
        return librarian_id;
    }

    public void setLibrarian_id(int librarian_id) {
        this.librarian_id = librarian_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public Date getWhenTake() {
        return whenTake;
    }

    public void setWhenTake(Date whenTake) {
        this.whenTake = whenTake;
    }

    public Date getWhenShouldReturn() {
        return whenShouldReturn;
    }

    public void setWhenShouldReturn(Date whenShouldReturn) {
        this.whenShouldReturn = whenShouldReturn;
    }

    public Date getWhenReturn() {
        return whenReturn;
    }

    public void setWhenReturn(Date whenReturn) {
        this.whenReturn = whenReturn;
    }

    public String getLibrarian() {
        return librarian;
    }

    public void setLibrarian(String librarian) {
        this.librarian = librarian;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
