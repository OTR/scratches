package otr.elib.framework.adapter.in.file.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import otr.elib.framework.exception.NoChapterOrdinalFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AccessPrivateMethodFromTests {

    /**
     * positive test case for PacktHtmlEpubMapper::extractChapterOrdinal
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
        assertEquals(expected1, reflectedMethod(input1));
        assertEquals(expected2, reflectedMethod(input2));
        assertEquals(expected3, reflectedMethod(input3));
    }

    /**
     * negative test case for PacktHtmlEpubMapper::extractChapterOrdinal
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
        ThrowingRunnable target1 = () -> reflectedMethod(input1);
        ThrowingRunnable target2 = () -> reflectedMethod(input2);
        ThrowingRunnable target3 = () -> reflectedMethod(input3);

        // THEN
        assertThrows(expected, target1);
        assertThrows(expected, target2);
        assertThrows(expected, target3);
    }

    /**
     * Access to private method PacktHtmlEpubMapper::extractChapterOrdinal
     * via reflection
     */
    private static int reflectedMethod(Document input) {
        try {
            Method method = PacktHtmlEpubParser
                .class
                .getDeclaredMethod("extractChapterOrdinal", Document.class);
            method.setAccessible(true);
            return (int) method.invoke(null, input);
        } catch (
            NoSuchMethodException |
            InvocationTargetException |
            IllegalAccessException e
        ) {
            if (e.getCause() instanceof NoChapterOrdinalFoundException inner) {
                throw inner;
            }
            throw new RuntimeException(e);
        }
    }

}
