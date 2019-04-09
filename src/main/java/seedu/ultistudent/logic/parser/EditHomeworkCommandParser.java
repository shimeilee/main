package seedu.ultistudent.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.commands.EditHomeworkCommand.MESSAGE_NOT_EDITED;
import static seedu.ultistudent.logic.commands.EditHomeworkCommand.MESSAGE_USAGE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.commands.EditHomeworkCommand.EditHomeworkDescriptor;
import seedu.ultistudent.logic.commands.EditHomeworkCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.tag.Tag;


/**
 * Parses input arguments and creates a new EditHomeworkCommand object
 */
public class EditHomeworkCommandParser implements Parser<EditHomeworkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditHomeworkCommand
     * and returns an EditHomeworkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditHomeworkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_HOMEWORK, PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        EditHomeworkDescriptor editHomeworkDescriptor = new EditHomeworkDescriptor();
        if (argMultimap.getValue(PREFIX_MODULECODE).isPresent()) {
            editHomeworkDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap
                                                            .getValue(PREFIX_MODULECODE).get()));
        }
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            editHomeworkDescriptor.setHomeworkName(ParserUtil.parseHomeworkName(argMultimap
                                                                .getValue(PREFIX_HOMEWORK).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editHomeworkDescriptor.setDeadline(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editHomeworkDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditHomeworkCommand(index, editHomeworkDescriptor);
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
