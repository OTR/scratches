package otr.elib.framework.adapter.in.file.mapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static otr.elib.framework.common.FileUtil.getAbsPathOfAppData;
import static otr.elib.framework.common.FileUtil.readTextFromFile;
import static otr.elib.framework.common.FileUtil.saveTextToFile;

public class PacktHtmlEpubMapperPackageTest {

    @Test
    public void provideValidHtml_savesSeparateHtmlFilesOnDisk() {
        // GIVEN
        String fileName = "B19916_01.xhtml"; // TODO: Provide test data
        String filePath = getAbsolutePathOfResource(fileName);
        String htmlSource = readTextFromFile(filePath);
        Document doc = Jsoup.parse(htmlSource);
        List<Document> docs = PacktHtmlEpubMapper.extractSubtitlesAsSeparateHtml(doc);
        for (int i = 0; i < docs.size(); i++) {
            Document document = docs.get(i);
            String patched = document.outerHtml();
            saveTextToFile(
                patched,
                getAbsPathOfAppData(i + ".html")
            );
        }
    }

    // TODO: Refactor, make it public util that return absolute path
    //  from package root
    private String getAbsolutePathOfResource(String fileName) {
        String filePath = new File(getClass()
            .getClassLoader()
            .getResource(fileName)
            .getFile())
            .getAbsolutePath();

        return filePath;
    }

}
