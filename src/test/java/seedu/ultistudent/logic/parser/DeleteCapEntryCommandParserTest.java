package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_CAP_ENTRY;

import org.junit.Test;

import seedu.ultistudent.logic.commands.DeleteCapEntryCommand;

public class DeleteCapEntryCommandParserTest {

    private DeleteCapEntryCommandParser parser = new DeleteCapEntryCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCapEntryCommand() {
        assertParseSuccess(parser, "1", new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //invalid type - character
        assertParseFailure(parser, "h", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCapEntryCommand.MESSAGE_USAGE));
        //invalid digit
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCapEntryCommand.MESSAGE_USAGE));
        //invalid digit
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCapEntryCommand.MESSAGE_USAGE));
        //mix of character and number
        assertParseFailure(parser, "0a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCapEntryCommand.MESSAGE_USAGE));
    }
}
