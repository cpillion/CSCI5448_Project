package com.csci5448.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {

    @Id
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

    public Sport getSport() { return sport; }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() { return author; }

    public String getBody() { return body; }

}
