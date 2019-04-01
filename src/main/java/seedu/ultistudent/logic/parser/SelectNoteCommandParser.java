package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ultistudent.commons.core.index.Index;

import seedu.ultistudent.logic.commands.SelectNoteCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new SelectNoteCommand object
 */
public class SelectNoteCommandParser implements Parser<SelectNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectNoteCommand
     * and returns an SelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
