package emris.control;

public enum Role {
    librarian, admin, reader;

    public String toName() {
        switch (this) {
            case admin:
                return "lib_admin";
            case reader:
                return "lib_reader";
            default:
                return "lib_librarian";
        }
    }
}
