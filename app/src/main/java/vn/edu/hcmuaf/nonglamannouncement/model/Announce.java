package vn.edu.hcmuaf.nonglamannouncement.model;

import android.media.Image;

import java.io.Serializable;

/**
 * @author Nguyen Hung
 * @version 1.0
 * Cai dat cac thuoc tinh va phuong thuc cua thong bao
 */
public class Announce implements Serializable {
    private String header;
    private String author;
    private String content;
    private Image img;


    public Announce(String header, String author, String content, Image img) {
        this.header = header;
        this.author = author;
        this.content = content;
        this.img = img;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
