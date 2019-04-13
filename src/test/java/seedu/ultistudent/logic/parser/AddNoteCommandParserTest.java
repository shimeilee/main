package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.ultistudent.logic.commands.AddNoteCommand;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.testutil.NoteBuilder;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_validArgs_returnsAddNoteCommand() {
        Note note = new NoteBuilder().build();
        assertParseSuccess(parser,
                " mc/CS2103T n/Tutorial 1",
                new AddNoteCommand(note));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser,
                "mc/ n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMcFormatArgs_throwsParseException() {
        assertParseFailure(parser,
                "mc/CSqqqqT n/Tutorial 5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNoteNameFormatArgs_throwsParseException() {
        assertParseFailure(parser, "mc/CS2103T n/__",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "mc/CS2103T n/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }
}
