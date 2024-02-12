package otr.elib.framework.exception;

import otr.elib.domain.exception.BaseAppException;

public class ParseChapterOrdinalFromBodyException extends BaseAppException {

    public static final String MESSAGE = "Chapter Ordinal parsed from `<title>` tag " +
    "and Chapter Ordinal parsed from `<body><h1>` tag are not equal";

    public ParseChapterOrdinalFromBodyException() {
        super(MESSAGE);

    }

    public ParseChapterOrdinalFromBodyException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
