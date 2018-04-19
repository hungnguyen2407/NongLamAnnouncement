package vn.edu.hcmuaf.nonglamannouncement.model;

public enum JSONTag {

    ANNOUNCE("announce");

    private String tag;

    JSONTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}
