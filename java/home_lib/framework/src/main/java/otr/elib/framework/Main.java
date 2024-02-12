package otr.elib.framework;

import otr.elib.framework.common.FileUtil;

import static otr.elib.framework.adapter.in.file.HtmlChapterInputAdapter.splitHtmlFromFIlePath;

public class Main {

    public static void main(String[] args) {
        String fileName = "B19916_15.xhtml";
        String absPath = FileUtil.getAbsPathOfAppData(fileName);
        splitHtmlFromFIlePath(absPath);
    }

}
