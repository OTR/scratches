package otr.elib.domain.entity;

import java.util.List;

public class Chapter {

    private static final String UNKNOWN_BOOK_TITLE = "";

    private final String bookTitle;
    private final int chapterOrdinal;
    private final String chapterTitle;
    private final List<Subtitle01> children;

    public Chapter(
        String bookTitle,
        int chapterOrdinal,
        String chapterTitle,
        List<Subtitle01> children
    ) {
        this.bookTitle = bookTitle;
        this.chapterOrdinal = chapterOrdinal;
        this.chapterTitle = chapterTitle;
        this.children = children;
    }

    public Chapter(
        int chapterOrdinal,
        String chapterTitle,
        List<Subtitle01> children
    ) {
        this.chapterOrdinal = chapterOrdinal;
        this.chapterTitle = chapterTitle;
        this.children = children;
        this.bookTitle = UNKNOWN_BOOK_TITLE;
    }

    public List<Subtitle01> getChildren() {
        return children;
    }

}
