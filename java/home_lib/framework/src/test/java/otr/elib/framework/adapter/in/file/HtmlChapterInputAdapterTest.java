package otr.elib.framework.adapter.in.file;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import otr.elib.application.use_case.HtmlChapterUseCase;
import otr.elib.domain.entity.Chapter;
import otr.elib.framework.exception.FileNotFoundAppException;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static otr.elib.framework.adapter.in.file.HtmlChapterInputAdapter
    .loadHtmlFromFilePath;

public class HtmlChapterInputAdapterTest {

    /**
     * Negative test case
     */
    @Test
    public void provideNotExistingFilePath_throwsFileNotFoundException() {
        // GIVEN
        HtmlChapterUseCase useCase = null;
        HtmlChapterInputAdapter htmlIA = new HtmlChapterInputAdapter(useCase);
        String fileName = "B19916_01.xhtml"; // FIXME: Provide a test data to git
        String filePath = getAbsolutePathOfResource(fileName);
        filePath = filePath.replace("B19916_01", "404");

        // WHEN
        String finalFilePath = filePath;
        ThrowingRunnable targetMethod = () ->
            loadHtmlFromFilePath(finalFilePath);

        // THEN
        assertThrows(
            FileNotFoundAppException.class,
            targetMethod
        );
    }

    @Test
    public void provideExistingFilePath_returnsValidChapter() {
        // GIVEN
        String fileName = "B19916_01.xhtml";
        String filePath = getAbsolutePathOfResource(fileName);

        // WHEN
        Chapter chapter = loadHtmlFromFilePath(filePath);

        // THEN
        assertNotNull(chapter);
    }

    private String getAbsolutePathOfResource(String fileName) {
        String filePath = new File(getClass()
            .getClassLoader()
            .getResource(fileName)
            .getFile())
            .getAbsolutePath();
        return filePath;
    }

}
