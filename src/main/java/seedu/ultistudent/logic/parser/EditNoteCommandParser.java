package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CliSyntax.NOTES_MANAGER;

import seedu.ultistudent.commons.core.index.Index;

import seedu.ultistudent.logic.commands.EditNoteCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.ui.StatusBarFooter;

/**
 * Parse input arguments and creates a new EditNoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an SelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        if (!StatusBarFooter.getCurrentManagerStatus().equals(NOTES_MANAGER)) {
            throw new ParseException("Please open Notes Manager before using 'edit-note' command.");
        }
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EditNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
