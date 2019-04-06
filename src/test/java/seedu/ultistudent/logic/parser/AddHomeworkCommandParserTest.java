package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.ultistudent.logic.commands.AddHomeworkCommand;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.testutil.HomeworkBuilder;

public class AddHomeworkCommandParserTest {
    private AddHomeworkCommandParser parser = new AddHomeworkCommandParser();

    @Test
    public void parse_validArgs_returnsAddHomeworkCommand() {
        Homework homework = new HomeworkBuilder().build();
        assertParseSuccess(parser,
                " mc/CS2103T hw/Tutorial 5 d/15/05/2019",
                new AddHomeworkCommand(homework));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser,
                "mc/ hw/ d/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMcFormatArgs_throwsParseException() {
        assertParseFailure(parser,
                "mc/CSqqqqT hw/Tutorial 5 d/15/05/2019.",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDeadlineFormatArgs_throwsParseException() {
        assertParseFailure(parser,
                "mc/CS2103T hw/Homework1 d/100/02/2001",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "mc/CS2103T hw/Homework1 d/10/20/2001",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "mc/CS2103T hw/Homework1 d/10/12/0000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
    }
}
