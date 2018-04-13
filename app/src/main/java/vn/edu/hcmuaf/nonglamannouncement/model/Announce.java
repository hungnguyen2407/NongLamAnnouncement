package vn.edu.hcmuaf.nonglamannouncement.model;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

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
    private Date date;

    public Announce(String header, String author, String content, Image img) {
        this.header = header;
        this.author = author;
        this.content = content;
        this.img = img;
        this.date = new Date(System.currentTimeMillis());
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

    public String getDate() {
        return date.getHours()+":"+date.getMinutes()+"\t"+date.getDay()+"/"+date.getMonth()+"/"+(date.getYear()+1900);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
