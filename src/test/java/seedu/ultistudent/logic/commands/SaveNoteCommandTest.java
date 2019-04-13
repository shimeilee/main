package seedu.ultistudent.logic.commands;

import static org.junit.Assert.fail;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertNoteCommandFailure;
import static seedu.ultistudent.logic.commands.CommandTestUtil.selectNoteAtIndex;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.ultistudent.testutil.TypicalNote.getTypicalAddressBook;

import org.junit.Test;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UserPrefs;

public class SaveNoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validSaveNote_command() {
        selectNoteAtIndex(model, INDEX_FIRST_NOTE);
        selectNoteAtIndex(expectedModel, INDEX_FIRST_NOTE);

        try {
            assertExecutionSuccess();
        } catch (CommandException ce) {
            fail(ce.getMessage());
        }
    }

    @Test
    public void execute_invalidSaveNote_command() {
        assertExecutionFailure();
    }

    /**
     * Execute a {@code SaveNoteCommand} and checks that model successfully saves the selected note.
     */
    private void assertExecutionSuccess() throws CommandException {
        SaveNoteCommand saveNoteCommand = new SaveNoteCommand();
        String expectedSaveMessage = String.format(SaveNoteCommand.MESSAGE_SAVE_NOTE_SUCCESS,
                                                    expectedModel.getSelectedNote());
        CommandResult commandResult = saveNoteCommand.execute(expectedModel, commandHistory);
        assertCommandSuccess(saveNoteCommand, model, commandHistory, expectedSaveMessage, expectedModel);
    }

    /**
     * Execute a {@code SaveNoteCommand} and checks that model successfully saves the selected note.
     */
    private void assertExecutionFailure() {
        SaveNoteCommand saveNoteCommand = new SaveNoteCommand();
        assertNoteCommandFailure(saveNoteCommand, model, commandHistory, saveNoteCommand.MESSAGE_NO_NOTE_SELECTED);
    }
}
