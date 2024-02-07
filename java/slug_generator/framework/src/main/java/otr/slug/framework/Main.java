package otr.slug.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import otr.slug.domain.exception.BaseCustomException;
import otr.slug.framework.adapter.in.stdin.option.ConsoleOption;
import otr.slug.framework.exception.UnrecognizedOptionHandler;

import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        CommandLineParser parser = new DefaultParser();
        String[] newArgs;

        try {
            CommandLine cmd = parser.parse(OPTIONS, args);
            parseOptions(cmd);
        } catch (UnrecognizedOptionException e) {
            newArgs = UnrecognizedOptionHandler.handle(args, e);
            LOGGER.debug("Options has been sanitized, and could be corrupted");
            try {
                CommandLine cmd = parser.parse(OPTIONS, newArgs);
                parseOptions(cmd);
            } catch (ParseException cause) {
                throw new BaseCustomException("Handler hasn't worked out", cause);
            }
        } catch (ParseException cause) {
            throw new BaseCustomException("Caught in Main class ", cause);
        }

    }

    private static Options configureOptions() {
        Options options = new Options();
        options.addOption(ConsoleOption.HELP);
        options.addOption(ConsoleOption.REST);
        options.addOption(ConsoleOption.TARGET);
        options.addOption(ConsoleOption.FILE);

        return options;
    }

    private static void parseOptions(CommandLine cmd) {
        // If `HELP` option is present -> show help message and exit
        if (cmd.hasOption(ConsoleOption.HELP.getOpt())) {
            displayHelp();
            return;
        }

        // Run in HTTP REST Server mode
        if (cmd.hasOption(ConsoleOption.REST.getOpt())) {
            App app = App.getRestApp();
            app.runRestWithNoArgs();
            return;
        }

        // If `TARGET` option is present
        if (cmd.hasOption(ConsoleOption.TARGET.getOpt())) {
            parseTargetMode(cmd);
            return;
        }

        // If `FILE` option is present -> run in JSON File based retrieve mode
        if (cmd.hasOption(ConsoleOption.FILE.getOpt()) ) {
            parseFileMode(cmd);
            return;
        }

        // When no special option has applied ->
        // Then trigger this `default` branch
        if (cmd.getArgs().length > 0 || cmd.getOptions().length > 0) {
            runDefaultBranch(cmd);
        }
    }

    private static void runDefaultBranch(CommandLine cmd) {
        String commandLineArgs = String.join(" ", cmd.getArgList());
        App app = App.getCliApp();
        app.runCliWithArgs(commandLineArgs);
    }

    private static void parseTargetMode(CommandLine cmd) {
        String[] args = cmd.getArgs();
        if (args.length == 1) {
            String path = args[0];
            if (path == null || path.isEmpty() || path.equals(".") || path.equals("..")) {
                throw new BaseCustomException();
            }
            Path normalizedPath = Paths.get(path).normalize();

            if (!normalizedPath.isAbsolute()) {
                normalizedPath = Paths.get(".").toAbsolutePath().resolve(path).normalize();
            }

            LOGGER.debug(normalizedPath.toString());
        } else {
            displayHelp();
        }
    }

    private static void parseFileMode(CommandLine cmd) {
        String[] args = cmd.getArgs();
        if (args.length > 0) {
            String userInput = String.join(" ", args);
            App app = App.getCliAppWithFilePersistence();
            app.runCliFilePersistenceOneArg(userInput);
        } else {
            App app = App.getCliAppWithFilePersistence();
            app.runCliFilePersistenceNoArgs();
        }
    }

    private static void displayHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(
            "slug_generator.sh [OPTION]... -t DIRECTORY SOURCE...\n"
                + "Rename SOURCE to DEST, or move SOURCE(s) to DIRECTORY.",
            OPTIONS
        );
    }


}
