package emris.control.tableInfo;

public class AccOrder {
    int placeId;
    int id;
    String bookName;
    String LibName;
    int libId;

    public AccOrder(int id,int placeId, String bookName, String libName, int libId) {
        this.placeId = placeId;
        this.id = id;
        this.bookName = bookName;
        LibName = libName;
        this.libId = libId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getLibName() {
        return LibName;
    }

    public void setLibName(String libName) {
        LibName = libName;
    }

    public int getLibId() {
        return libId;
    }

    public void setLibId(int libId) {
        this.libId = libId;
    }
}
