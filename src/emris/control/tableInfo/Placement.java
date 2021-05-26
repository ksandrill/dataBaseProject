package emris.control.tableInfo;

public class Placement {
    int id;
    int rack;
    int shell;
    int hall;
    String status;
    String library;

    public Placement(int id, int rack, int shell, int hall, String status, String library) {
        this.id = id;
        this.rack = rack;
        this.shell = shell;
        this.hall = hall;
        this.status = status;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    public int getShell() {
        return shell;
    }

    public void setShell(int shell) {
        this.shell = shell;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
