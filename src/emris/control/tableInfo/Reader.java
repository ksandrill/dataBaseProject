package emris.control.tableInfo;

import java.sql.Date;

public class Reader {
    private int id;
    private Date lastVisitTime;
    private String name;
    private String surname;
    private String roleType;
    private int booksOnHands;
    public Reader(){

    }

    public Reader(int id, String name, String surname, String roleType,Date lastVisitTime,int booksOnHands) {
        this.id = id;
        this.lastVisitTime = lastVisitTime;
        this.name = name;
        this.surname = surname;
        this.roleType = roleType;
        this.booksOnHands = booksOnHands;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
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

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public int getBooksOnHands() {
        return booksOnHands;
    }

    public void setBooksOnHands(int booksOnHands) {
        this.booksOnHands = booksOnHands;
    }
}
