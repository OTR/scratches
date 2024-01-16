package com.github.otr.domain.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/*

 */
public record FeedEntry(
        String title,
        String description,
        String link,
        String guId,
        List<FeedCategory> categories,
        String author,
        Date publicationDate,
        String content
) {

    public String asArticle() {
        StringBuilder sb = new StringBuilder()
                .append("Title:\n")
                .append(this.title).append("\n")
                .append("Description:\n")
                .append(this.description).append("\n")
                .append("Link:\n")
                .append(this.link).append("\n")
                .append("guId:\n")
                .append(this.guId).append("\n")
                .append("Categories:\n")
                .append(this.categories.toString()).append("\n")
                .append("Author:\n") // TODO: authors (handle plural)
                .append(this.author).append("\n")
                .append("Published at:\n")
                .append(this.publicationDate).append("\n")
                .append("Content:\n")
                .append(this.content).append("\n");

        return sb.toString();
    }

}
