package otr.elib.framework.adapter.in.file.mapper;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.exception.NoChapterOrdinalFoundException;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * An HTML Parser for a certain Chapter
 */
public class PacktHtmlEpubMapper {

    private static final Pattern CHAPTER_ORDINAL = Pattern.compile(".+_([0-9]+)$");
    private static final String CONTAINER_FOR_SUBTITLES = "body > div[id~=Container]";

    public static Chapter toDomain(String contents) {
        Document document = Jsoup.parse(contents);
        int ordinal = extractChapterOrdinal(document);
        String title = extractChapterTitle(document);
        List<Subtitle01> children = extractChapterSubtitles(document);
        Chapter chapter = new Chapter(ordinal, title, children);

        return chapter;
    }

    static List<Subtitle01> extractChapterSubtitles(Document document) {
        Elements targetDiv = document.select(CONTAINER_FOR_SUBTITLES);
        Elements children = requireNonNull(targetDiv.first()).children();

        for (Element child : children) {
//            System.out.println(
//                child.tagName()
//                + " with ID: "
//                + child.id()
//                + " : "
//                + requireNonNull(child.text()).substring(0, Math.min(child.text().length(), 40))
//            );
            System.out.print(
                "\"" + child.tagName() + "\", "
            );
        }

        return Collections.emptyList();
    }

    private static String extractChapterTitle(Document document) {
        return null;
    }

    /**
     * <title>B19916_01</title> -> 1
     */
    private static int extractChapterOrdinal(Document document) {
        String title = document.title();
        Matcher ordinalMatcher = CHAPTER_ORDINAL.matcher(title);
        if (ordinalMatcher.find()) {
            return Integer.parseInt(ordinalMatcher.group(1));
        } else {
            throw new NoChapterOrdinalFoundException(title);
        }
    }


}
