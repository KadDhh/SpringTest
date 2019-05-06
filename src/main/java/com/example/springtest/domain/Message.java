package com.example.springtest.domain;

import javax.persistence.*;

@Entity // This tells SQL to make a table out of this class

public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(String text, User user) {
        this.text = text;
        this.author = user;
    }

    public String getAuthorName(){
        if (author!= null){
            return author.getUsername();
        } else return "<автора нет>";
    }


    private String text;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER) //messages to user
    @JoinColumn(name="user_id")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
