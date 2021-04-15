package emris.control.tableInfo;

public class Book {
    int id;
    String name;
    boolean onHands;

    public Book(int id, String name, boolean onHands) {
        this.id = id;
        this.name = name;
        this.onHands = onHands;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnHands() {
        return onHands;
    }

    public void setOnHands(boolean onHands) {
        this.onHands = onHands;
    }
}
