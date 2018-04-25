package vn.edu.hcmuaf.nonglamannouncement.model;

public enum JSONTags {

    USER("id"),
    FACULTY("faculty_id"),
    GROUP("class_id"),
    GROUP_LIST("infoGroupOfUser"),
    GROUP_NAME("class_name"),
    GROUP_FACULTY("class_faculty"),
    GROUP_MEM("class_mem_num"),
    ANNOUNCE("announce"), USER_LNAME("lName"), USER_FNAME("fName");

    JSONTags(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
