package vn.edu.hcmuaf.nonglamannouncement.model;

public enum NameOfResult {

    USER_ID("user_id"), USER_NAME("user_name"), USER_FACULTY_ID("user_faculty_id"), USER_FACULTY_NAME("user_faculty_name"), USER_CLASS_ID("user_class_id"), USER_CLASS_NAME("user_class_name"), LOGIN_SUCCESS("login_success"), ANNOUNCE_DATA("announce_data");

    NameOfResult(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
