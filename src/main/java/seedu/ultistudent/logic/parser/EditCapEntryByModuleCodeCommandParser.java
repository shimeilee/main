package seedu.ultistudent.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.ultistudent.logic.commands.EditCapEntryByModuleCodeCommand;
import seedu.ultistudent.logic.commands.EditCapEntryByModuleCodeCommand.EditCapEntryDescriptor;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Parses input arguments and creates a new EditCapEntryCommand object
 */
public class EditCapEntryByModuleCodeCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCapEntryByModuleCodeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_MODULEGRADE,
                PREFIX_MODULECREDITS, PREFIX_SEMESTER);

        ModuleCode moduleCode;

        try {
            moduleCode = ParserUtil.parseHomeworkModuleCode(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCapEntryByModuleCodeCommand.MESSAGE_USAGE), pe);
        }

        EditCapEntryDescriptor editCapEntryDescriptor = new EditCapEntryDescriptor();
        if (argMultimap.getValue(PREFIX_MODULECODE).isPresent()) {
            editCapEntryDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap
                            .getValue(PREFIX_MODULECODE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULEGRADE).isPresent()) {
            editCapEntryDescriptor.setModuleGrade(ParserUtil.parseModuleGrade(argMultimap
                    .getValue(PREFIX_MODULEGRADE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULECREDITS).isPresent()) {
            editCapEntryDescriptor.setModuleCredits(ParserUtil.parseModuleCredits(argMultimap
                            .getValue(PREFIX_MODULECREDITS).get()));
        }
        if (argMultimap.getValue(PREFIX_SEMESTER).isPresent()) {
            editCapEntryDescriptor.setModuleSemester(ParserUtil.parseModuleSemester(argMultimap
                    .getValue(PREFIX_SEMESTER).get()));
        }

        if (!editCapEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCapEntryByModuleCodeCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCapEntryByModuleCodeCommand(moduleCode, editCapEntryDescriptor);
    }

}

