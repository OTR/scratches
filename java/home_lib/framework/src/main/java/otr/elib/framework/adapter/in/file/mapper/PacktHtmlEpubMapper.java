package otr.elib.framework.adapter.in.file.mapper;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.domain.exception.BaseAppException;
import otr.elib.framework.exception.NoChapterOrdinalFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * An HTML Parser for a certain Chapter
 */
public class PacktHtmlEpubMapper {

    private static final Pattern CHAPTER_ORDINAL = Pattern.compile(".+_([0-9]+)$");
    private static final String CONTAINER_FOR_SUBTITLES = "body div[id~=Container]";
    public static final String SUBTITLE_H1_TAG = "h1";

    public static Chapter toDomain(String contents) {
        Document document = Jsoup.parse(contents);
        int ordinal = extractChapterOrdinal(document);
        String title = extractChapterTitle(document);

        throw new BaseAppException("Not Implemented yet!");
        //List<Subtitle01> children = extractChapterSubtitles(document);
        //Chapter chapter = new Chapter(ordinal, title, Collections.emptyList());

        //return chapter;
    }

    public static List<Subtitle01> extractChapterSubtitles(final Document document) {
        throw new BaseAppException("Not implemented yet");
    }

    // TODO: make in private again
    public static List<Document> extractSubtitlesAsSeparateHtml(final Document document) {
        Elements targetDiv = document.select(CONTAINER_FOR_SUBTITLES);
        Element container = requireNonNull(targetDiv.first());
        Elements children = container.children();
        List<Document> output = new ArrayList<>();
        container.empty();
        document.outputSettings().prettyPrint(true);

        List<List<Element>> groups = breakParagraphsIntoGroups(children);
        Element chapterOrdinalH1 = groups.get(0).get(0);
        Element chapterTitleH1 = groups.get(1).get(0);
        groups.remove(0);
        for (List<Element> group : groups) {
            final Document prototype = document.clone();
            final Element protoContainer = prototype.select(CONTAINER_FOR_SUBTITLES).first();
            requireNonNull(protoContainer);
//            group.add(getHighLightJsSnippet()); // TODO: Inject HighLightJs
            protoContainer.appendChildren(group);
            output.add(prototype);
        }

        return output;
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

    private static List<List<Element>> breakParagraphsIntoGroups(Elements children) {
        List<List<Element>> groups = new ArrayList<>();
        List<Element> currParagraph = new ArrayList<>();

        for (Element element : children) {
            if (element.nameIs(SUBTITLE_H1_TAG)) {
                if (!currParagraph.isEmpty()) {
                    groups.add(currParagraph);
                }
                currParagraph = new ArrayList<>();
            }
            currParagraph.add(element);
        }

        if (!currParagraph.isEmpty()) {
            groups.add(currParagraph);
        }

        return groups;
    }

}
