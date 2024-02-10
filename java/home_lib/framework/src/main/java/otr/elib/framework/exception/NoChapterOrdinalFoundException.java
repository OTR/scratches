package otr.elib.framework.exception;

import otr.elib.domain.exception.BaseAppException;

public class NoChapterOrdinalFoundException extends BaseAppException {

    private static final String message
        = "No Chapter Ordinal Found that would match regular expression\n" +
        "Expected: looks like `B19916_01`\n" +
        "Actual: ";

    public NoChapterOrdinalFoundException(String title) {
        super(message + title);
    }

}
