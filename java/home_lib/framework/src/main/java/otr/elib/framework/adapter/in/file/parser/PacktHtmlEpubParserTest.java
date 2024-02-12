package otr.elib.framework.adapter.in.file.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import otr.elib.framework.exception.NoChapterOrdinalFoundException;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser
    .extractSubtitlesAsSeparateHtml;
import static otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser
    .getChapterOrdinalFromBody;
import static otr.elib.framework.adapter.in.file.parser.PacktHtmlEpubParser
    .getChapterOrdinalFromTitle;
import static otr.elib.framework.common.FileUtil.getAbsPathOfAppData;
import static otr.elib.framework.common.FileUtil.readTextFromFile;
import static otr.elib.framework.common.FileUtil.saveTextToFile;

public class PacktHtmlEpubParserTest {

    private static final Document _document;
    // TODO: Provide test data file along with repository
    private static final String TEST_DATA_FILE = "B19916_01.xhtml";

    static {
        String filePath = getAbsolutePathOfResource(TEST_DATA_FILE);
        String htmlSource = readTextFromFile(filePath);
        _document = Jsoup.parse(htmlSource);
    }

    public static Document getDocument() {
        return _document.clone();
    }

    /**
     * positive test case for
     * PacktHtmlEpubMapper::getChapterOrdinalFromTitle
     */
    @Test
    public void provideValidHtmlTitle_matchChapterOrdinal() {
        // GIVEN
        Document input1 = Jsoup.parse("<title>B19916_01</title>");
        Document input2 = Jsoup.parse("<title>B1991X_11</title>");
        Document input3 = Jsoup.parse("<title>B1991D_101</title>");
        int expected1 = 1;
        int expected2 = 11;
        int expected3 = 101;

        // WHEN && THEN
        assertEquals(expected1, getChapterOrdinalFromTitle(input1));
        assertEquals(expected2, getChapterOrdinalFromTitle(input2));
        assertEquals(expected3, getChapterOrdinalFromTitle(input3));
    }

    /**
     * negative test case for
     * PacktHtmlEpubMapper::getChapterOrdinalFromTitle
     */
    @Test
    public void provideInvalidHtmlTitle_throwsNoOrdinalFound() {
        // GIVEN
        Document input1 = Jsoup.parse("<title>Chapter 01</title>");
        Document input2 = Jsoup.parse("<title>B1991X11</title>");
        Document input3 = Jsoup.parse("<title>101</title>");
        Class<NoChapterOrdinalFoundException> expected
            = NoChapterOrdinalFoundException.class;

        // WHEN
        ThrowingRunnable target1 = () -> getChapterOrdinalFromTitle(input1);
        ThrowingRunnable target2 = () -> getChapterOrdinalFromTitle(input2);
        ThrowingRunnable target3 = () -> getChapterOrdinalFromTitle(input3);

        // THEN
        assertThrows(expected, target1);
        assertThrows(expected, target2);
        assertThrows(expected, target3);
    }

    @Test
    public void provideValidHtml_savesSeparateHtmlFilesOnDisk() {
        // GIVEN
        final Document inputDoc = getDocument();

        // WHEN
        List<Document> docs = extractSubtitlesAsSeparateHtml(inputDoc);
        for (int i = 0; i < docs.size(); i++) {
            Document document = docs.get(i);
            String patched = document.outerHtml();
            saveTextToFile(
                patched,
                getAbsPathOfAppData("%d.html".formatted(i + 1))
            );
        }

        // THEN

    }

    @Test
    public void provideValidHtml_matchesChapterOrdinalFromBody() {
        // GIVEN
        final Document inputDoc = getDocument();
        int expected = 1;

        // WHEN
        int actual = getChapterOrdinalFromBody(inputDoc);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void provideValidHtml_matchesChapterOrdinalFromTitle() {
        // GIVEN
        final Document inputDoc = getDocument();
        int expected = 1;

        // WHEN
        int actual = getChapterOrdinalFromTitle(inputDoc);

        // THEN
        assertEquals(expected, actual);
    }

    // TODO: Refactor, make it public util that return absolute path
    //  from package root
    private static String getAbsolutePathOfResource(String fileName) {
        String filePath = new File(PacktHtmlEpubParserTest.class
            .getClassLoader()
            .getResource(fileName)
            .getFile())
            .getAbsolutePath();

        return filePath;
    }

}
