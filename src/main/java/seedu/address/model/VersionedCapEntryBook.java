package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code CapEntryBook} that keeps track of its own history.
 */
public class VersionedCapEntryBook extends CapEntryBook {
    private final List<ReadOnlyCapEntryBook> capEntryBookStateList;
    private int currentStatePointer;

    public VersionedCapEntryBook(ReadOnlyCapEntryBook initialState) {
        super(initialState);

        capEntryBookStateList = new ArrayList<>();
        capEntryBookStateList.add(new CapEntryBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        capEntryBookStateList.add(new CapEntryBook(this));
        currentStatePointer++;
        indicateModified();
    }

    private void removeStatesAfterCurrentPointer() {
        capEntryBookStateList.subList(currentStatePointer + 1, capEntryBookStateList.size()).clear();
    }

    /**
     * Restores the CapManager to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new VersionedCapEntryBook.NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(capEntryBookStateList.get(currentStatePointer));
    }

    /**
     * Restores the UltiStudent to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new VersionedCapEntryBook.NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(capEntryBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has UltiStudent states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has UltiStudent states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < capEntryBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedCapEntryBook)) {
            return false;
        }

        VersionedCapEntryBook otherVersionedAddressBook = (VersionedCapEntryBook) other;

        // state check
        return super.equals(otherVersionedAddressBook)
                && capEntryBookStateList.equals(otherVersionedAddressBook.capEntryBookStateList)
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}
