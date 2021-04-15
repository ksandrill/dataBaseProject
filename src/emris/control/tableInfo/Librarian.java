package emris.control.tableInfo;

public class Librarian {
    private int id;
    private String name;
    private String surname;
    private String library;

    public Librarian(int id, String name, String surname, String library) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.library = library;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
