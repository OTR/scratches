package otr.elib.framework.adapter.in.file;

import otr.elib.application.use_case.HtmlChapterUseCase;
import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.adapter.in.file.mapper.PacktHtmlEpubMapper;

import static otr.elib.framework.common.FileUtil.readTextFile;

public class HtmlChapterInputAdapter {

    private HtmlChapterUseCase useCase;

    public HtmlChapterInputAdapter(HtmlChapterUseCase useCase) {
        this.useCase = useCase;
    }

    public Chapter loadHtmlFromFilePath(String filePath) {
        String contents = readTextFile(filePath);
        Chapter chapter = PacktHtmlEpubMapper.toDomain(contents);
        if (chapter != null) {
            chapter.getChildren().stream()
                .map(Subtitle01::getTitle)
                .forEach(System.out::println);
        }

        return chapter;
    }


}
