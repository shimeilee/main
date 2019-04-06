package seedu.ultistudent.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_THIRD_NOTE;
import static seedu.ultistudent.testutil.TypicalNote.getTypicalAddressBook;

import org.junit.Test;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code EditNoteCommandTest}.
 */
public class EditNoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index lastNoteIndex = Index.fromOneBased(model.getFilteredNoteList().size());

        assertExecutionSuccess(INDEX_FIRST_NOTE);
        assertExecutionSuccess(INDEX_THIRD_NOTE);
        assertExecutionSuccess(lastNoteIndex);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);
        showNoteAtIndex(expectedModel, INDEX_FIRST_NOTE);

        assertExecutionSuccess(INDEX_FIRST_NOTE);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);
        showNoteAtIndex(expectedModel, INDEX_FIRST_NOTE);

        Index outOfBoundsIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundIndex is still in bounds of UltiStudent list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getNoteList().size());

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditNoteCommand editNoteFirstCommand = new EditNoteCommand(INDEX_FIRST_NOTE);
        EditNoteCommand editNoteSecondCommand = new EditNoteCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(editNoteFirstCommand.equals(editNoteFirstCommand));

        // same values -> returns true
        EditNoteCommand editNoteFirstCommandCopy = new EditNoteCommand(INDEX_FIRST_NOTE);
        assertTrue(editNoteFirstCommand.equals(editNoteFirstCommandCopy));

        // different types -> returns false
        assertFalse(editNoteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(editNoteFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(editNoteFirstCommand.equals(editNoteSecondCommand));
    }

    /**
     * Executes a {@code EditNoteCommand} with the given {@code index},
     * and checks that the model's selected note is set to the note at {@code index} in the filtered note list.
     */
    private void assertExecutionSuccess(Index index) {
        EditNoteCommand editNoteCommand = new EditNoteCommand(index);
        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, index.getOneBased());
        expectedModel.setSelectedNote(model.getFilteredNoteList().get(index.getZeroBased()));

        assertCommandSuccess(editNoteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code EditNoteCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        EditNoteCommand editNoteCommand = new EditNoteCommand(index);
        assertCommandFailure(editNoteCommand, model, commandHistory, expectedMessage);
    }
}
