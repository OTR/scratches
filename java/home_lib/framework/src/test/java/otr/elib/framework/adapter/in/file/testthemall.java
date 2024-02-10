package otr.elib.framework.adapter.in.file;

import java.util.List;

import java.util.ArrayList;

class ParagraphSplitter {

    public static List<List<String>> splitIntoParagraphs(List<String> inputTags) {
        List<List<String>> groups = new ArrayList<>();
        List<String> currentParagraph = new ArrayList<>();

        for (String tag : inputTags) {
            if (tag.equals("h1")) {
                if (!currentParagraph.isEmpty()) {
                    groups.add(currentParagraph);
                }
                currentParagraph = new ArrayList<>();
            }
            currentParagraph.add(tag);
        }

        // Add the last paragraph
        if (!currentParagraph.isEmpty()) {
            groups.add(currentParagraph);
        }

        return groups;
    }

    public static void main(String[] args) {
        List<String> inputTags = List.of(
            "h1", "h1", "p", "p", "p", "p", "h1", "p", "p", "ul", "p", "div", "p", "p", "p", "h1", "p", "p", "p", "div", "p", "div", "p", "p", "p", "p", "p", "ul", "p", "p", "p", "p", "h1", "p", "p", "p", "p", "div", "p", "p", "div", "p", "p", "p", "div", "p", "h1", "p", "p", "p", "ul", "p", "p", "div", "div", "p", "p", "h1", "p", "p", "div", "p", "p", "p", "p"
        );
        List<List<String>> paragraphs = splitIntoParagraphs(inputTags);
        System.out.println(paragraphs);
    }
}
