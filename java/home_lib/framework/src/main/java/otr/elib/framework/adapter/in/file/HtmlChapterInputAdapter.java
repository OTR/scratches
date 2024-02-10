package otr.elib.framework.adapter.in.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import otr.elib.application.use_case.HtmlChapterUseCase;
import otr.elib.domain.entity.Chapter;
import otr.elib.domain.entity.Subtitle01;
import otr.elib.framework.adapter.in.file.mapper.HtmlEpubMapper;

public class HtmlChapterInputAdapter {

    private HtmlChapterUseCase useCase;

    public HtmlChapterInputAdapter(HtmlChapterUseCase useCase) {
        this.useCase = useCase;
    }

    public void loadHtmlFromFilePath(String filePath) {
        String contents = readTextFile(filePath);
        Chapter chapter = HtmlEpubMapper.toDomain(contents);
        if (chapter != null) {
            chapter.getChildren().stream()
                .map(Subtitle01::getTitle)
                .forEach(System.out::println);
        }
    }

     private String readTextFile(String filePath) {

        try (BufferedReader br = new BufferedReader(
            new FileReader(filePath)
        )) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
