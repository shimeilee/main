package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCapEntryByModuleCodeCommand;
import seedu.address.logic.commands.EditCapEntryByModuleCodeCommand.EditCapEntryDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.tag.Tag;

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
                PREFIX_MODULECREDITS, PREFIX_SEMESTER, PREFIX_TAG);

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
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editCapEntryDescriptor::setTags);

        if (!editCapEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCapEntryByModuleCodeCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCapEntryByModuleCodeCommand(moduleCode, editCapEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}

