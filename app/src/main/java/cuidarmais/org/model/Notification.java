package cuidarmais.org.model;

import java.util.Date;

/**
 * Created by deco on 20/09/17.
 */

public class Notification {

    private String id;
    private Date date;
    private String title;
    private String text;
    private String author;


    public Notification() {

    }

    public Notification(Date date,String title, String text, String author){
        this.date = date;
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public Notification(String id, Date date, String text, String author){
        this.id = id;
        this.date = date;
        this.text = text;
        this.author = author;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append("Data: ").append(date.toString());
        str.append("Título: ").append(title);
        str.append("Aviso: ").append(text);
        str.append("Autor: ").append(author);
        return str.toString();
    }
}
