package otr.elib.framework.adapter.in.file.mapper;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.exception.NoChapterOrdinalFoundException;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PacktHtmlEpubMapper {

    private static final Pattern ORDINAL_REGEX = Pattern.compile(".+_([0-9]+)$");

    public static Chapter toDomain(String contents) {
        Document document = Jsoup.parse(contents);
        int ordinal = extractChapterOrdinal(document);
        String title = extractChapterTitle(document);
        List<Subtitle01> children = extractChapterSubtitles(document);
        Chapter chapter = new Chapter(ordinal, title, children);
        return chapter;
    }

    private static List<Subtitle01> extractChapterSubtitles(Document document) {
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
        Matcher ordinalMatcher = ORDINAL_REGEX.matcher(title);
        if (ordinalMatcher.find()) {
            return Integer.parseInt(ordinalMatcher.group(1));
        } else {
            throw new NoChapterOrdinalFoundException(title);
        }
    }


}
