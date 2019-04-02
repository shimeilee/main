package seedu.ultistudent.logic.parser;

import seedu.ultistudent.logic.commands.OpenCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new OpenCommand object.
 */
public class OpenCommandParser implements Parser<OpenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OpenCommand
     * and returns an OpenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public OpenCommand parse(String args) throws ParseException {

        String[] argsParts = args.split(" ");
        if (argsParts.length < 2) {
            throw new ParseException(String.format(OpenCommand.MESSAGE_USAGE));
        }
        return new OpenCommand(argsParts[1]);
    }
}
