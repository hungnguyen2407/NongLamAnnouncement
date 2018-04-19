package vn.edu.hcmuaf.nonglamannouncement.model;

public enum AnnounceData {

    HEADER("header"), AUTHOR("author"), CONTENT("content"), DATE("date");

    private String name;

    AnnounceData(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
