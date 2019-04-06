package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_NOTE;

import org.junit.Test;

import seedu.ultistudent.logic.commands.EditNoteCommand;

/**
 * Test scope: similar to {@code SelectCommandParserTest}.
 * @see SelectCommandParserTest
 */
public class EditNoteCommandParserTest {

    private EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new EditNoteCommand(INDEX_FIRST_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE));
    }
}
