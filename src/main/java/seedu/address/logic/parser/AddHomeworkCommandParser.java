package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.homework.Date;
import seedu.address.model.homework.Homework;
import seedu.address.model.homework.HomeworkName;
import seedu.address.model.homework.ModuleCode;

/**
 * Parses input arguments and creates a new AddHomeworkCommad object
 */
public class AddHomeworkCommandParser implements Parser<AddHomeworkCommand> {

    /**
     * Parses the given {@code args} of arguments in the context of the AddHomeworkCommand
     * and returns an AddHomeworkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddHomeworkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HOMEWORK, PREFIX_MODULECODE, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_HOMEWORK, PREFIX_MODULECODE, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
        }

        HomeworkName homeworkName = ParserUtil.parseHomeworkName(argMultimap.getValue(PREFIX_HOMEWORK).get());
        ModuleCode moduleCode = ParserUtil.parseHomeworkModuleCode(argMultimap.getValue(PREFIX_MODULECODE).get());
        Date deadline = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE).get());

        Homework homework = new Homework(moduleCode, homeworkName, deadline);
        return new AddHomeworkCommand(homework);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
