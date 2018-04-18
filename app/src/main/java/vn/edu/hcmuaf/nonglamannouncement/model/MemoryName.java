package vn.edu.hcmuaf.nonglamannouncement.model;

public enum MemoryName {
    TEMP_DATA("temp_data"), LOGIN_DATA("login_data");

    MemoryName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
