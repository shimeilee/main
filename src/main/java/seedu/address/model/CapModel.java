package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.cap.CapEntry;

public interface CapModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<CapEntry> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' UltiStudent file path.
     */
    Path getCapEntryBookFilePath();

    /**
     * Sets the user prefs' UltiStudent file path.
     */
    void setCapEntryBookFilePath(Path capEntryBookFilePath);

    /**
     * Replaces UltiStudent data with the data in {@code addressBook}.
     */
    void setCapEntryBook(ReadOnlyCapEntryBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyCapEntryBook getCapEntryBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UltiStudent.
     */
    boolean hasCapEntry(CapEntry capEntry);

    /**
     * Deletes the given person.
     * The person must exist in the UltiStudent.
     */
    void deleteCapEntry(CapEntry target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the UltiStudent.
     */
    void addCapEntry(CapEntry capEntry);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the UltiStudent.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UltiStudent.
     */
    void setCapEntry(CapEntry target, CapEntry editedCapEntry);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<CapEntry> getFilteredCapEntriesList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCapEntriesList(Predicate<CapEntry> predicate);

    /**
     * Returns true if the model has previous UltiStudent states to restore.
     */
    boolean canUndoCapEntryBook();

    /**
     * Returns true if the model has undone UltiStudent states to restore.
     */
    boolean canRedoCapEntryBook();

    /**
     * Restores the model's UltiStudent to its previous state.
     */
    void undoCapEntryBook();

    /**
     * Restores the model's UltiStudent to its previously undone state.
     */
    void redoCapEntryBook();

    /**
     * Saves the current UltiStudent state for undo/redo.
     */
    void commitCapEntryBook();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<CapEntry> selectedCapEntryProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    CapEntry getSelectedCapEntry();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedCapEntry(CapEntry capEntry);

}
