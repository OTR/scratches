package otr.elib.framework.exception;

import otr.elib.domain.exception.BaseAppException;

public class HtmlOrdinalParseException extends BaseAppException {

    public static final String MESSAGE = "Chapter Ordinal parsed from `<title>` tag " +
    "and Chapter Ordinal parsed from `<body><h1>` tag are not equal";

    public HtmlOrdinalParseException() {
        super(MESSAGE);

    }

}
