package vn.edu.hcmuaf.nonglamannouncement.model;

public enum ObjectTypes {

    USER("id"), FACULTY("faculty_id"), GROUP("class_id");

    ObjectTypes(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
