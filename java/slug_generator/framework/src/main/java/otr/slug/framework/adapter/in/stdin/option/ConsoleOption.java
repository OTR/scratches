package otr.slug.framework.adapter.in.stdin.option;

import org.apache.commons.cli.Option;

/**
 * TODO: Rewrite with ENUM class
 */
public final class ConsoleOption {

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

    public static final Option REST = Option
        .builder("r")
        .longOpt("rest")
        .hasArg(HAS_NO_ARG)
        .desc("Run in HTTP REST Server mode")
        .build();

    public static final Option TARGET = Option
        .builder("t")
        .longOpt("target-directory")
        // TODO:
        //  -t, --target-directory=DIRECTORY
        .hasArg(HAS_NO_ARG)
        .desc("move all SOURCE arguments into DIRECTORY")
        .build();

}
