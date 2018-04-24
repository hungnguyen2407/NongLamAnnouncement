package vn.edu.hcmuaf.nonglamannouncement.model;

public enum ObjectTypes {

    USER("id"), FACULTY("faculty_id"), GROUP("class_id"), GROUP_LIST("infoGroupOfUser"), GROUP_NAME("class_name"), GROUP_FACULTY("class_faculty"), GROUP_MEM("class_mem_num");

    ObjectTypes(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
