package seedu.ultistudent.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.DESC_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.DESC_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1002;
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
import seedu.ultistudent.logic.commands.EditCapEntryCommand.EditCapEntryDescriptor;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.UserPrefs;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.testutil.CapEntryBuilder;
import seedu.ultistudent.testutil.EditCapEntryDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCapEntryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        CapEntry editedCapEntry = new CapEntryBuilder().build();
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder(editedCapEntry).build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, descriptor);

        String expectedMessage = String.format(EditCapEntryCommand.MESSAGE_EDIT_CAP_ENTRY_SUCCESS, editedCapEntry);

        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());
        expectedModel.setCapEntry(model.getFilteredCapEntryList().get(0), editedCapEntry);
        expectedModel.commitUltiStudent();

        assertCommandSuccess(editCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCapEntry = Index.fromOneBased(model.getFilteredCapEntryList().size());
        CapEntry lastCapEntry = model.getFilteredCapEntryList().get(indexLastCapEntry.getZeroBased());

        CapEntryBuilder capEntryInList = new CapEntryBuilder(lastCapEntry);
        CapEntry editedCapEntry = capEntryInList.withModuleCode(VALID_MODULE_CODE_CS1002)
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).build();

        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002)
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(indexLastCapEntry, descriptor);

        String expectedMessage = String.format(EditCapEntryCommand.MESSAGE_EDIT_CAP_ENTRY_SUCCESS, editedCapEntry);

        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());
        expectedModel.setCapEntry(lastCapEntry, editedCapEntry);
        expectedModel.commitUltiStudent();

        assertCommandSuccess(editCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY,
                new EditCapEntryDescriptor());
        CapEntry editedCapEntry = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCapEntryCommand.MESSAGE_EDIT_CAP_ENTRY_SUCCESS, editedCapEntry);

        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());
        expectedModel.commitUltiStudent();

        assertCommandSuccess(editCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCapEntryAtIndex(model, INDEX_FIRST_CAP_ENTRY);

        CapEntry capEntryInFilteredList = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        CapEntry editedCapEntry = new CapEntryBuilder(capEntryInFilteredList).withModuleCode(VALID_MODULE_CODE_CS1002)
                .build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY,
                new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002).build());

        String expectedMessage = String.format(EditCapEntryCommand.MESSAGE_EDIT_CAP_ENTRY_SUCCESS, editedCapEntry);

        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());
        expectedModel.setCapEntry(model.getFilteredCapEntryList().get(0), editedCapEntry);
        expectedModel.commitUltiStudent();

        assertCommandSuccess(editCapEntryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCapEntryUnfilteredList_failure() {
        CapEntry firstCapEntry = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder(firstCapEntry).build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_SECOND_CAP_ENTRY, descriptor);

        assertCommandFailure(editCapEntryCommand, model, commandHistory,
                EditCapEntryCommand.MESSAGE_DUPLICATE_CAP_ENTRY);
    }

    @Test
    public void execute_duplicateCapEntryFilteredList_failure() {
        showCapEntryAtIndex(model, INDEX_FIRST_CAP_ENTRY);

        // edit person in filtered list into a duplicate in UltiStudent
        CapEntry capEntryInList = model.getAddressBook().getCapEntryList().get(INDEX_SECOND_CAP_ENTRY.getZeroBased());
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY,
                new EditCapEntryDescriptorBuilder(capEntryInList).build());
        assertCommandFailure(editCapEntryCommand, model, commandHistory,
                EditCapEntryCommand.MESSAGE_DUPLICATE_CAP_ENTRY);
    }

    @Test
    public void execute_invalidCapEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCapEntryList().size() + 1);
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002)
                .build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of UltiStudent
     */
    @Test
    public void execute_invalidCapEntryIndexFilteredList_failure() {
        showCapEntryAtIndex(model, INDEX_FIRST_CAP_ENTRY);
        Index outOfBoundIndex = INDEX_SECOND_CAP_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of UltiStudent list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCapEntryList().size());

        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(outOfBoundIndex,
                new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002).build());

        assertCommandFailure(editCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        CapEntry editedCapEntry = new CapEntryBuilder().build();
        CapEntry capEntryToEdit = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder(editedCapEntry).build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, descriptor);
        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());
        expectedModel.setCapEntry(capEntryToEdit, editedCapEntry);
        expectedModel.commitUltiStudent();

        // edit -> first cap entry edited
        editCapEntryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered cap entry list to show all cap entries
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first cap entry edited again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCapEntryList().size() + 1);
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002)
                .build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(outOfBoundIndex, descriptor);

        // execution failed -> UltiStudent state not added into model
        assertCommandFailure(editCapEntryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);

        // single UltiStudent state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code CapEntry} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited person in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the capEntry object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameCapEntryEdited() throws Exception {
        CapEntry editedCapEntry = new CapEntryBuilder().build();
        EditCapEntryDescriptor descriptor = new EditCapEntryDescriptorBuilder(editedCapEntry).build();
        EditCapEntryCommand editCapEntryCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, descriptor);
        Model expectedModel = new ModelManager(new UltiStudent(model.getAddressBook()), new UserPrefs());

        showCapEntryAtIndex(model, INDEX_SECOND_CAP_ENTRY);
        CapEntry capEntryToEdit = model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased());
        expectedModel.setCapEntry(capEntryToEdit, editedCapEntry);
        expectedModel.commitUltiStudent();

        // edit -> edits second person in unfiltered person list / first person in filtered person list
        editCapEntryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(model.getFilteredCapEntryList().get(INDEX_FIRST_CAP_ENTRY.getZeroBased()), capEntryToEdit);
        // redo -> edits same second person in unfiltered person list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditCapEntryCommand standardCommand = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, DESC_CS1001);

        // same values -> returns true
        EditCapEntryDescriptor copyDescriptor = new EditCapEntryDescriptor(DESC_CS1001);
        EditCapEntryCommand commandWithSameValues = new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCapEntryCommand(INDEX_SECOND_CAP_ENTRY, DESC_CS1001)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCapEntryCommand(INDEX_FIRST_CAP_ENTRY, DESC_CS1002)));
    }
}
