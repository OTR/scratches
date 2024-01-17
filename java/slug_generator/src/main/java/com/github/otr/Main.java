package com.github.otr;

import com.github.otr.feature.normalization.Normalizer;
import com.github.otr.feature.options.Argument;

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
        options.addOption(Argument.HELP);

        return options;
    }

    private static void parseOptions(CommandLine cmd) {
        if (cmd.hasOption(Argument.HELP.getOpt())) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("slug_generator", OPTIONS);
        // When no special option has applied
        // Then trigger this `default` branch
        } else if (cmd.getArgs().length > 0) {
            String argsTogether = String.join(" ", cmd.getArgList());
            String result = Normalizer.normalize(argsTogether);
            System.out.println(result);
        }
    }

}
