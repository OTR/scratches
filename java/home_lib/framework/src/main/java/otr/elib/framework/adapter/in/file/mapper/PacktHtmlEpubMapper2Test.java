package otr.elib.framework.adapter.in.file.mapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import otr.elib.domain.entity.Subtitle01;

import java.io.File;
import java.util.List;

import static otr.elib.framework.common.FileUtil.readTextFile;


public class PacktHtmlEpubMapper2Test {

    @Test
    public void provideValidHtml_getsAListOfChildren() {
        // GIVEN
        String fileName = "B19916_01.xhtml";
        String filePath = getAbsolutePathOfResource(fileName);
        String htmlSource = readTextFile(filePath);
        Document doc = Jsoup.parse(htmlSource);
        List<?> elems = PacktHtmlEpubMapper.extractChapterSubtitles(doc);

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
