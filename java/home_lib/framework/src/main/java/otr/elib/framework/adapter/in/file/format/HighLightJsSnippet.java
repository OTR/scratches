package otr.elib.framework.adapter.in.file.format;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class HighLightJsSnippet {

    private static final Element HIGH_LIGHT_JS_SNIPPET
        = Jsoup.parseBodyFragment("""
        <script type="text/javascript">hljs.highlightAll();</script>""")
        .selectFirst("script");

    public static Element getHighLightJsSnippet() {
        return HIGH_LIGHT_JS_SNIPPET.clone();
    }

}
