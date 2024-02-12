package otr.elib.framework.adapter.in.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import otr.elib.application.use_case.HtmlChapterUseCase;
import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.adapter.in.file.parser.ChapterParsingResponse;
import otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser;
import otr.elib.framework.adapter.in.file.parser.SubtitleFile;

import java.util.List;

import static otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser
    .prepareParsingResponse;
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
        ChapterParsingResponse response = prepareParsingResponse(doc);
        List<SubtitleFile> subtitles = response.subtitles();
        for (SubtitleFile subtitle : subtitles) {
            String patched = subtitle.fileContent();
            String filename = subtitle.filename();
            saveTextToFile(
                patched,
                getAbsPathOfAppData(filename)
            );
        }
    }

}
