package vn.edu.hcmuaf.nonglamannouncement.model;

public class Group {
    private String groupId;
    private String groupName;
    private String facultyId;
    private int memNum;

    public Group(String groupId, String groupName, String facultyId, int memNum) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.facultyId = facultyId;
        this.memNum = memNum;
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

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }
}
