package vn.edu.hcmuaf.nonglamannouncement.model;

public class Group {
    private String groupId;
    private String groupName;
    private String facultyId;

    public Group(String groupId, String groupName, String facultyId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.facultyId = facultyId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}
