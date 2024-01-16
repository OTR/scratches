package com.github.otr.feature.options;

import org.apache.commons.cli.Option;

/**
 * TODO: Rewrite with ENUM class
 */
public final class Argument {

    private static final String SHORT_OPTION = "h";
    private static final String LONG_OPTION = "help";
    private static final boolean HAS_NO_ARG = false;
    private static final String COMMAND_DESCRIPTION = "This is a command line utility" +
            " for text processing";

    public static final Option HELP = Option
            .builder(SHORT_OPTION)
            .longOpt(LONG_OPTION)
            .hasArg(HAS_NO_ARG)
            .desc(COMMAND_DESCRIPTION)
            .build();

}
