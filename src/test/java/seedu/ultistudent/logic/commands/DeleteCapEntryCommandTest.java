package seedu.ultistudent.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.logic.commands.CommandTestUtil.showCapEntryAtIndex;
import static seedu.ultistudent.testutil.TypicalCapEntry.getTypicalAddressBook;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_CAP_ENTRY;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_SECOND_CAP_ENTRY;

import org.junit.Test;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UserPrefs;
import seedu.ultistudent.model.cap.CapEntry;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCapEntryCommand}.
 */
public class DeleteCapEntryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        CapEntry capEntryToDelete = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);

        String expectedMessage = String.format(DeleteCapEntryCommand.MESSAGE_DELETE_CAP_ENTRY_SUCCESS,
                capEntryToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCapEntry(capEntryToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCapEntryList().size() + 1);
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(outOfBoundIndex);

        assertCommandFailure(deleteCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCapEntryAtIndex(model, INDEX_FIRST_CAP_ENTRY);

        CapEntry capEntryToDelete = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);

        String expectedMessage = String.format(DeleteCapEntryCommand.MESSAGE_DELETE_CAP_ENTRY_SUCCESS,
                capEntryToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCapEntry(capEntryToDelete);
        expectedModel.commitAddressBook();
        showNoCapEntry(expectedModel);

        assertCommandSuccess(deleteCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCapEntryAtIndex(model, INDEX_FIRST_CAP_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_CAP_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of UltiStudent list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCapEntryList().size());

        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(outOfBoundIndex);

        assertCommandFailure(deleteCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        CapEntry capEntryToDelete = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCapEntry(capEntryToDelete);
        expectedModel.commitAddressBook();

        // delete -> first person deleted
        deleteCapEntryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first person deleted again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCapEntryList().size() + 1);
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(outOfBoundIndex);

        // execution failed -> UltiStudent state not added into model
        assertCommandFailure(deleteCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);

        // single UltiStudent state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Person} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted person in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the person object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameCapEntryDeleted() throws Exception {
        DeleteCapEntryCommand deleteCapEntryCommand = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showCapEntryAtIndex(model, INDEX_SECOND_CAP_ENTRY);
        CapEntry capEntryToDelete = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        expectedModel.deleteCapEntry(capEntryToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second person in unfiltered person list / first person in filtered person list
        deleteCapEntryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(capEntryToDelete, model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased()));
        // redo -> deletes same second person in unfiltered person list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCapEntryCommand deleteFirstCapEntryCommand = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);
        DeleteCapEntryCommand deleteSecondCapEntryCommand = new DeleteCapEntryCommand(INDEX_SECOND_CAP_ENTRY);

        // same object -> returns true
        assertTrue(deleteFirstCapEntryCommand.equals(deleteFirstCapEntryCommand));

        // same values -> returns true
        DeleteCapEntryCommand deleteFirstCapEntryCommandCopy = new DeleteCapEntryCommand(INDEX_FIRST_CAP_ENTRY);
        assertTrue(deleteFirstCapEntryCommand.equals(deleteFirstCapEntryCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCapEntryCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCapEntryCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCapEntryCommand.equals(deleteSecondCapEntryCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCapEntry(Model model) {
        model.updateFilteredCapEntryList(c -> false);

        assertTrue(model.getFilteredCapEntryList().isEmpty());
    }
}
