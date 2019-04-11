package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CREDITS_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CREDITS_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_GRADE_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_GRADE_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_SEMESTER_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_SEMESTER_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ultistudent.testutil.CapEntryBuilder.DEFAULT_MODULE_CODE;
import static seedu.ultistudent.testutil.CapEntryBuilder.DEFAULT_MODULE_CREDITS;
import static seedu.ultistudent.testutil.CapEntryBuilder.DEFAULT_MODULE_GRADE;
import static seedu.ultistudent.testutil.CapEntryBuilder.DEFAULT_MODULE_SEMESTER;
import static seedu.ultistudent.testutil.TypicalCapEntry.CS1002;

import org.junit.Test;

import seedu.ultistudent.logic.commands.AddCapEntryCommand;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.testutil.CapEntryBuilder;

public class AddCapEntryCommandParserTest {
    private AddCapEntryCommandParser parser = new AddCapEntryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CapEntry expectedCapEntry = new CapEntryBuilder(CS1002).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS1002 + MODULE_GRADE_DESC_CS1002
                + MODULE_CREDITS_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1002, new AddCapEntryCommand(expectedCapEntry));

        // multiple module code - last module code accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS1001 + MODULE_CODE_DESC_CS1002
                + MODULE_GRADE_DESC_CS1002 + MODULE_CREDITS_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1002,
                new AddCapEntryCommand(expectedCapEntry));

        // multiple grades - last grade accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS1002 + MODULE_GRADE_DESC_CS1001
                        + MODULE_GRADE_DESC_CS1002 + MODULE_CREDITS_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1002,
                new AddCapEntryCommand(expectedCapEntry));

        // multiple module credits - last module credits accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS1002 + MODULE_GRADE_DESC_CS1002
                        + MODULE_CREDITS_DESC_CS1001 + MODULE_CREDITS_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1002,
                new AddCapEntryCommand(expectedCapEntry));

        // multiple semesters - last semester accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS1002 + MODULE_GRADE_DESC_CS1002
                        + MODULE_CREDITS_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1001 + MODULE_SEMESTER_DESC_CS1002,
                new AddCapEntryCommand(expectedCapEntry));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCapEntryCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, " CS2109 g/A- mcs/4 sem/y2s2", expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, " mc/CS2101 A+ mcs/4 sem/y2s2", expectedMessage);

        // missing modular credits prefix
        assertParseFailure(parser, " mc/CS2101 g/A+ 4 sem/y1s2", expectedMessage);

        // missing module semester prefix
        assertParseFailure(parser, " mc/cs2101 g/A+ mcs/4 y3s1", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " cs2101 A+ 4 y1s2", expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsAddCapEntryCommand() {
        CapEntry capEntry = new CapEntryBuilder().build();
        assertParseSuccess(parser,
                " mc/" + DEFAULT_MODULE_CODE + " g/" + DEFAULT_MODULE_GRADE + " mcs/" + DEFAULT_MODULE_CREDITS
                        + " sem/" + DEFAULT_MODULE_SEMESTER,
                new AddCapEntryCommand(capEntry));
    }

    //    @Test
    //    public void parse_emptyArgs_throwsParseException() {
    //        assertParseFailure(parser,
    //                " mc/ g/ mcs/ sem/",
    //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCapEntryCommand.MESSAGE_USAGE));
    //    }
    //
    //    @Test
    //    public void parse_invalidMcFormatArgs_throwsParseException() {
    //        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCapEntryCommand.MESSAGE_USAGE);
    //        //non-numbers for module code
    //        assertParseFailure(parser," mc/CSABCDET g/A mcs/4 sem/Y1S1", expectedMessage);
    //        //more than 4 numbers for module code
    //        assertParseFailure(parser," mc/CS12341 g/A mcs/4 sem/Y1S1", expectedMessage);
    //        //more than 3 leading characters for module code
    //        assertParseFailure(parser," mc/ABCD1234T g/A mcs/4 sem/Y1S1", expectedMessage);
    //        //more than 1 trailing character for module code
    //        assertParseFailure(parser, " mc/GER1000 g/A mcs/4 sem/Y1S1", expectedMessage);
    //    }

    @Test
    public void parse_invalidGradeFormatArgs_throwsParseException() {
    }

}
