package otr.slug.framework;

import otr.slug.framework.adapter.in.stdin.option.ConsoleOption;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * An entry point to a console program that executes
 * a text processing over supplied string
 *
 * Arguments:
 *  $ slug_generator <some-string> - a basic version of providing arguments
 *
 */
public class Main {

    public static final Options OPTIONS = configureOptions();

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(OPTIONS, args);
            parseOptions(cmd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static Options configureOptions() {
        Options options = new Options();
        options.addOption(ConsoleOption.HELP);
        options.addOption(ConsoleOption.REST);

        return options;
    }

    private static void parseOptions(CommandLine cmd) {
        // If `HELP` option is present -> show help message and exit
        if (cmd.hasOption(ConsoleOption.HELP.getOpt())) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("slug_generator", OPTIONS);
            return;
        }

        // Run in HTTP REST Server mode
        if (cmd.hasOption(ConsoleOption.REST.getOpt())) {
            App app = App.getRestApp();
            app.runRestWithNoArgs();
        }

        // When no special option has applied ->
        // Then trigger this `default` branch
        if (cmd.getArgs().length > 0) {
            String commandLineArgs = String.join(" ", cmd.getArgList());
            App app = App.getCliApp();
            app.runCliWithArgs(commandLineArgs);
        }
    }

}
