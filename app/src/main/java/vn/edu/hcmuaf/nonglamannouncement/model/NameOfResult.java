package vn.edu.hcmuaf.nonglamannouncement.model;

public enum NameOfResult {

    USER_NAME("user_name"), ANNOUNCE_DATA("announce_data");

    NameOfResult(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
