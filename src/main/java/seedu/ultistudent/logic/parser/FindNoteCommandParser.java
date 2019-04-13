package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.ultistudent.logic.commands.FindNoteCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.note.NoteNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindNoteCommandParser implements Parser<FindNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindNoteCommand and returns an FindNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindNoteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNoteCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindNoteCommand(new NoteNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
