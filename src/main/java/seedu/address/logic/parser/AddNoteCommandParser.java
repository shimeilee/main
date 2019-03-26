package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteName;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddNoteCommand and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse (String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_NOTE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULECODE, PREFIX_NOTE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format
                    (MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseNoteModuleCode(argMultimap
                .getValue(PREFIX_MODULECODE).get());
        NoteName noteName = ParserUtil.parseNoteName(argMultimap.getValue
                (PREFIX_NOTE_NAME).get());

        Note note = new Note(moduleCode, noteName, new Content(" "));

        return new AddNoteCommand(note);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
