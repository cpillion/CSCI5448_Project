package com.csci5448.content;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private final Sport sport;
    @Column
    private final String headline;
    @Column
    private final String author;
    @Column
    private final String body;

    public News(Sport sport, String headline, String author, String body) {
        this.sport = sport;
        this.headline = headline;
        this.author = author;
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Sport getSport() { return sport; }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() { return author; }

    public String getBody() { return body; }

}
