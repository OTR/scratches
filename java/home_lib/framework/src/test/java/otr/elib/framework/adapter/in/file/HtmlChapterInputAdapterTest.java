package otr.elib.framework.adapter.in.file;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import otr.elib.application.use_case.HtmlChapterUseCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import static org.junit.Assert.assertThrows;

public class HtmlChapterInputAdapterTest {

    @Test
    public void provideNotExistingFilePath_throwsFileNotFoundException() {
        // GIVEN
        HtmlChapterUseCase useCase = null;
        HtmlChapterInputAdapter htmlIA = new HtmlChapterInputAdapter(useCase);
        String fileName = "404.xhtml";
        String filePath = new File(getClass()
                .getClassLoader()
                .getResource(fileName)
                .getFile())
            .getAbsolutePath();

        // WHEN
        ThrowingRunnable targetFunction = () ->
            htmlIA.loadHtmlFromFilePath(filePath);

        // THEN
        assertThrows(
            FileNotFoundException.class,
            targetFunction
        );
    }

    void x() {
        Path relativePath = null;


        System.out.println(relativePath);
    }

}
