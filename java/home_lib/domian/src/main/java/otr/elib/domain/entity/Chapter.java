package otr.elib.domain.entity;

import java.util.List;

public class Chapter {

    private String bookTitle;
    private int chapterOrdinal;
    private String chapterTitle;
    private List<Subtitle01> children;

    public List<Subtitle01> getChildren() {
        return children;
    }

}
