package otr.elib.framework.adapter.in.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import otr.elib.application.use_case.HtmlChapterUseCase;
import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser;

import java.util.List;

import static otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser
    .extractSubtitlesAsSeparateHtml;
import static otr.elib.framework.common.FileUtil.getAbsPathOfAppData;
import static otr.elib.framework.common.FileUtil.readTextFromFile;
import static otr.elib.framework.common.FileUtil.saveTextToFile;

public class HtmlChapterInputAdapter {

    private HtmlChapterUseCase useCase;

    public HtmlChapterInputAdapter(HtmlChapterUseCase useCase) {
        this.useCase = useCase;
    }

    public static Chapter loadHtmlFromFilePath(String filePath) {
        String contents = readTextFromFile(filePath);
        Chapter chapter = PacktHtmlEpubParser.toDomain(contents);
        if (chapter != null) {
            chapter.getChildren().stream()
                .map(Subtitle01::getTitle)
                .forEach(System.out::println);
        }

        return chapter;
    }

    public static void splitHtmlFromFIlePath(String filePath) {
        String htmlSource = readTextFromFile(filePath);
        Document doc = Jsoup.parse(htmlSource);
        List<Document> docs = extractSubtitlesAsSeparateHtml(doc);
        for (int i = 0; i < docs.size(); i++) {
            Document document = docs.get(i);
            String patched = document.outerHtml();
            saveTextToFile(
                patched,
                getAbsPathOfAppData(i + ".html")
            );
        }
    }

}
