package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCapEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.cap.ModuleCredits;
import seedu.address.model.cap.ModuleGrade;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCapEntryCommand object
 */
public class AddCapEntryCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCapEntryCommand
     * and returns an AddCapEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCapEntryCommand parse (String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_MODULEGRADE, PREFIX_MODULECREDITS,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULECODE, PREFIX_MODULEGRADE, PREFIX_MODULECREDITS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCapEntryCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULECODE).get());
        ModuleGrade moduleGrade = ParserUtil.parseModuleGrade(argMultimap.getValue(PREFIX_MODULEGRADE).get());
        ModuleCredits moduleCredits = ParserUtil.parseModuleCredits(argMultimap.getValue(PREFIX_MODULECREDITS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        CapEntry capEntry = new CapEntry(moduleCode, moduleGrade, moduleCredits, tagList);

        return new AddCapEntryCommand(capEntry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
