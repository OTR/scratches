package otr.elib.framework.adapter.in.file.parser;

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
public class PacktHtmlEpubParser {

    private static final Pattern CHAPTER_ORDINAL = Pattern.compile(".+_([0-9]+)$");
    private static final String CONTAINER_FOR_SUBTITLES = "body div[id~=Container]";
    public static final String SUBTITLE_H1_TAG = "h1";

    public static Chapter toDomain(String contents) {
        final Document document = Jsoup.parse(contents);
        int ordinalFromTitle = getChapterOrdinalFromTitle(document);
        int ordinalFromBody = getChapterOrdinalFromBody(document);
        doubleCheckOrdinal(ordinalFromBody, ordinalFromTitle);
        String title = getChapterTitleFromBody(document);

        throw new BaseAppException("Not Implemented yet!");
        //List<Subtitle01> children = extractChapterSubtitles(document);
        //Chapter chapter = new Chapter(ordinal, title, Collections.emptyList());

        //return chapter;
    }

    private static void doubleCheckOrdinal(int ordinalFromBody, int ordinalFromTitle) {
        if (ordinalFromBody != ordinalFromTitle) {
            throw new BaseAppException();
        }
    }

    public static List<Subtitle01> extractChapterSubtitles(final Document document) {
        throw new BaseAppException("Not implemented yet");
    }

    // TODO: make in private again
    public static List<Document> extractSubtitlesAsSeparateHtml(final Document document) {
        Elements children = extractContainerChildren(document);
        List<List<Element>> groups = breakParagraphsIntoGroups(children);
        groups.remove(0);

        List<Document> independentDocs = new ArrayList<>();
        for (List<Element> group : groups) {
            final Document prototypeDoc = getDocWithEmptyContainer(document);
            final Document filledDoc = fillUpDocWithChildren(prototypeDoc, group);
            independentDocs.add(filledDoc);
        }

        return independentDocs;
    }

    private static Document fillUpDocWithChildren(
        final Document document,
        List<Element> group
    ) {
        final Document filledDoc = document.clone();
        final Element emptyContainer = filledDoc
            .select(CONTAINER_FOR_SUBTITLES).first();
        requireNonNull(emptyContainer);
//            group.add(getHighLightJsSnippet()); // TODO: Inject HighLightJs
        emptyContainer.appendChildren(group);
        return filledDoc;
    }

    /**
     * <title>B19916_01</title> -> 1
     */
    static int getChapterOrdinalFromTitle(Document document) {
        String title = document.title();
        Matcher ordinalMatcher = CHAPTER_ORDINAL.matcher(title);
        if (ordinalMatcher.find()) {
            return Integer.parseInt(ordinalMatcher.group(1));
        } else {
            throw new NoChapterOrdinalFoundException(title);
        }
    }

    static int getChapterOrdinalFromBody(final Document document) {
        Elements children = extractContainerChildren(document);
        List<List<Element>> groups = breakParagraphsIntoGroups(children);
        Element h1WithChapterOrdinal = groups.get(0).get(0);

        try {
            return Integer.parseInt(h1WithChapterOrdinal.text().strip());
        } catch (NumberFormatException e) {
            throw new BaseAppException(e);
        }
    }

    private static String getChapterTitleFromBody(final Document document) {
        Elements children = extractContainerChildren(document);
        List<List<Element>> groups = breakParagraphsIntoGroups(children);
        Element h1WithChapterTitle = groups.get(1).get(0);

        return h1WithChapterTitle.text();
    }

    private static Elements extractContainerChildren(final Document document) {
        Elements targetDiv = document.clone()
            .select(CONTAINER_FOR_SUBTITLES);
        Element container = requireNonNull(targetDiv.first());

        return container.children();
    }

    private static Document getDocWithEmptyContainer(final Document document) {
        Document documentWithEmptyContainer = document.clone();
        Elements resultMatches = documentWithEmptyContainer
            .select(CONTAINER_FOR_SUBTITLES);
        Element container = requireNonNull(resultMatches.first());
        container.empty();

        return documentWithEmptyContainer;
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
