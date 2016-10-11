package com.csci5448.content;

public class News {

    private final Sport sport;
    private final String headline;
    private final String author;
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
