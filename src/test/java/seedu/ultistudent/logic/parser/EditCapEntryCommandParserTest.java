package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.ultistudent.logic.commands.CommandTestUtil.INVALID_MODULE_CREDITS_DESC;
import static seedu.ultistudent.logic.commands.CommandTestUtil.INVALID_MODULE_GRADE_DESC;
import static seedu.ultistudent.logic.commands.CommandTestUtil.INVALID_MODULE_SEMESTER_DESC;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CREDITS_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_CREDITS_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_GRADE_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_GRADE_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_SEMESTER_DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.MODULE_SEMESTER_DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CREDITS_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CREDITS_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1002;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ultistudent.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_CAP_ENTRY;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_SECOND_CAP_ENTRY;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_THIRD_CAP_ENTRY;

import org.junit.Test;

import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.commands.EditCapEntryCommand;
import seedu.ultistudent.logic.commands.EditCapEntryCommand.EditCapEntryDescriptor;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.testutil.EditCapEntryDescriptorBuilder;

public class EditCapEntryCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCapEntryCommand.MESSAGE_USAGE);

    private EditCapEntryCommandParser parser = new EditCapEntryCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE_CODE_CS1001, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCapEntryCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MODULE_CODE_DESC_CS1001, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "01234" + MODULE_CODE_DESC_CS1001, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "abc def", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 wrong/ prefix", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
        // invalid module grade
        assertParseFailure(parser, "1" + INVALID_MODULE_GRADE_DESC, ModuleGrade.MESSAGE_CONSTRAINTS);
        // invalid module credits
        assertParseFailure(parser, "1" + INVALID_MODULE_CREDITS_DESC, ModuleCredits.MESSAGE_CONSTRAINTS);
        // invalid module semester
        assertParseFailure(parser, "1" + INVALID_MODULE_SEMESTER_DESC, ModuleSemester.MESSAGE_CONSTRAINTS);

        // invalid grade followed by valid credits
        assertParseFailure(parser, "1" + INVALID_MODULE_GRADE_DESC + MODULE_CREDITS_DESC_CS1001,
                ModuleGrade.MESSAGE_CONSTRAINTS);

        // valid grade followed by invalid grade. The test case for invalid grade followed by valid grade
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + MODULE_GRADE_DESC_CS1002 + INVALID_MODULE_GRADE_DESC,
                ModuleGrade.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC + INVALID_MODULE_CREDITS_DESC
                        + VALID_MODULE_SEMESTER_CS1001 + VALID_MODULE_GRADE_CS1001,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CAP_ENTRY;
        String userInput = targetIndex.getOneBased() + MODULE_GRADE_DESC_CS1002 + MODULE_CREDITS_DESC_CS1001
                + MODULE_SEMESTER_DESC_CS1001 + MODULE_CODE_DESC_CS1001;

        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1001)
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).withModuleCredits(VALID_MODULE_CREDITS_CS1001)
                .withModuleSemester(VALID_MODULE_SEMESTER_CS1001)
                .build();
        EditCapEntryCommand expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CAP_ENTRY;
        String userInput = targetIndex.getOneBased() + MODULE_GRADE_DESC_CS1002 + MODULE_CREDITS_DESC_CS1001;

        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder()
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).withModuleCredits(VALID_MODULE_CREDITS_CS1001).build();
        EditCapEntryCommand expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // module code
        Index targetIndex = INDEX_THIRD_CAP_ENTRY;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_CS1001;
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1001)
                .build();
        EditCapEntryCommand expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module grade
        userInput = targetIndex.getOneBased() + MODULE_GRADE_DESC_CS1001;
        descriptor = new EditCapEntryDescriptorBuilder().withModuleGrade(VALID_MODULE_GRADE_CS1001).build();
        expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module credits
        userInput = targetIndex.getOneBased() + MODULE_CREDITS_DESC_CS1001;
        descriptor = new EditCapEntryDescriptorBuilder().withModuleCredits(VALID_MODULE_CREDITS_CS1001).build();
        expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module semester
        userInput = targetIndex.getOneBased() + MODULE_SEMESTER_DESC_CS1001;
        descriptor = new EditCapEntryDescriptorBuilder().withModuleSemester(VALID_MODULE_SEMESTER_CS1001).build();
        expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CAP_ENTRY;
        String userInput = targetIndex.getOneBased() + MODULE_GRADE_DESC_CS1001 + MODULE_SEMESTER_DESC_CS1001
                + MODULE_CREDITS_DESC_CS1001 + MODULE_GRADE_DESC_CS1001 + MODULE_SEMESTER_DESC_CS1001
                + MODULE_CREDITS_DESC_CS1001 + MODULE_GRADE_DESC_CS1002 + MODULE_SEMESTER_DESC_CS1002
                + MODULE_CREDITS_DESC_CS1002;

        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder()
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).withModuleCredits(VALID_MODULE_CREDITS_CS1002)
                .withModuleSemester(VALID_MODULE_SEMESTER_CS1002).build();
        EditCapEntryCommand expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_CAP_ENTRY;
        String userInput = targetIndex.getOneBased() + INVALID_MODULE_GRADE_DESC + MODULE_GRADE_DESC_CS1002;
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder()
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).build();
        EditCapEntryCommand expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + MODULE_CREDITS_DESC_CS1002 + INVALID_MODULE_GRADE_DESC
                + MODULE_SEMESTER_DESC_CS1002 + MODULE_GRADE_DESC_CS1002;
        descriptor = new EditCapEntryDescriptorBuilder().withModuleGrade(VALID_MODULE_GRADE_CS1002)
                .withModuleCredits(VALID_MODULE_CREDITS_CS1002).withModuleSemester(VALID_MODULE_SEMESTER_CS1002)
                .build();
        expectedCommand = new EditCapEntryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
