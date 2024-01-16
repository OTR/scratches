package com.github.otr.data.remote;

import com.github.otr.domain.entity.FeedCategory;
import com.github.otr.domain.entity.FeedEntry;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*

 */
public class RssFetcherTest {

    @Test
    public void testBasicUsage() throws IOException, FeedException {
        String url = "https://economist.kg/latest/rss/";
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));

        SyndEntry latestFeed = feed.getEntries().get(0);

        String title = latestFeed.getTitle();
        SyndContent _description = latestFeed.getDescription();
        String link = latestFeed.getLink();
        String guId = latestFeed.getUri();
        List<SyndCategory> _categories = latestFeed.getCategories();
        String author = latestFeed.getAuthor();
        Date pubDate = latestFeed.getPublishedDate();
        List<SyndContent> _content = latestFeed.getContents();

        String description = convertDescription(_description);
        List<FeedCategory> categories = convertCategories(_categories);
        String content = convertContent(_content);

        FeedEntry latestEntry = new FeedEntry(
                title,
                description,
                link,
                guId,
                categories,
                author,
                pubDate,
                content
        );


        System.out.println(latestEntry.asArticle());
        System.out.println();
    }

    private String convertContent(List<SyndContent> contents) {
        StringBuilder output = new StringBuilder();
        for (SyndContent content : contents) {
            if (content.getType().equals("html")) {
                // Strip HTML tags using a suitable library
                // This example uses Jsoup for convenience
                String text = Jsoup.parse(content.getValue()).text();
                output.append(text);
            } else {
                // Handle other content types as needed
                output.append(content.getValue());
            }
        }

        return output.toString();
    }

    private List<FeedCategory> convertCategories(List<SyndCategory> categories) {
        return List.of(); // TODO: Implement this method
    }


    private String convertDescription(SyndContent description) {
        var type = description.getType();
        var value = description.getValue();

        if (type.equals("text/html")) {
            // Strip HTML tags using a suitable library
            // This example uses Jsoup for convenience
            String text = Jsoup.parse(value).text();
            return text;
        } else {
            // Handle other content types as needed
            return description.getValue();
        }
    }

}