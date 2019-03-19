package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class CapModelManager implements CapModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedCapEntryBook versionedCapEntryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<CapEntry> filteredCapEntriesList;
    private final SimpleObjectProperty<CapEntry> selectedCapEntry = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public CapModelManager(ReadOnlyCapEntryBook capEntryBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(capEntryBook, userPrefs);

        logger.fine("Initializing with UltiStudent: " + capEntryBook + " and user prefs " + userPrefs);

        versionedCapEntryBook = new VersionedCapEntryBook(capEntryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCapEntriesList = new FilteredList<>(versionedCapEntryBook.getCapEntryList());
        filteredCapEntriesList.addListener(this::ensureSelectedCapEntryIsValid);
    }

    public CapModelManager() {
        this(new CapEntryBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCapEntryBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setCapEntryBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== CapEntryBook ================================================================================

    @Override
    public void setCapEntryBook(ReadOnlyCapEntryBook capEntryBook) {
        versionedCapEntryBook.resetData(capEntryBook);
    }

    @Override
    public ReadOnlyCapEntryBook getCapEntryBook() {
        return versionedCapEntryBook;
    }

    @Override
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return versionedCapEntryBook.hasCapEntry(capEntry);
    }

    @Override
    public void deleteCapEntry(CapEntry target) {
        versionedCapEntryBook.removeCapEntry(target);
    }

    @Override
    public void addCapEntry(CapEntry capEntry) {
        versionedCapEntryBook.addCapEntry(capEntry);
        updateFilteredCapEntriesList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireAllNonNull(target, editedCapEntry);

        versionedCapEntryBook.setCapEntry(target, editedCapEntry);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<CapEntry> getFilteredCapEntriesList() {
        return filteredCapEntriesList;
    }

    @Override
    public void updateFilteredCapEntriesList(Predicate<CapEntry> predicate) {
        requireNonNull(predicate);
        filteredCapEntriesList.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoCapEntryBook() {
        return versionedCapEntryBook.canUndo();
    }

    @Override
    public boolean canRedoCapEntryBook() {
        return versionedCapEntryBook.canRedo();
    }

    @Override
    public void undoCapEntryBook() {
        versionedCapEntryBook.undo();
    }

    @Override
    public void redoCapEntryBook() {
        versionedCapEntryBook.redo();
    }

    @Override
    public void commitCapEntryBook() {
        versionedCapEntryBook.commit();
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
        return selectedCapEntry;
    }

    @Override
    public CapEntry getSelectedCapEntry() {
        return selectedCapEntry.getValue();
    }

    @Override
    public void setSelectedCapEntry(CapEntry capEntry) {
        if (capEntry != null && !filteredCapEntriesList.contains(capEntry)) {
            throw new PersonNotFoundException();
        }
        selectedCapEntry.setValue(capEntry);
    }


    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedCapEntryIsValid(ListChangeListener.Change<? extends CapEntry> change) {
        while (change.next()) {
            if (selectedCapEntry.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedCapEntryReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedCapEntry.getValue());
            if (wasSelectedCapEntryReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedCapEntry.getValue());
                selectedCapEntry.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedCapEntry.getValue().isSameCapEntry(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedCapEntry.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        CapModelManager other = (CapModelManager) obj;
        return versionedCapEntryBook.equals(other.versionedCapEntryBook)
                && userPrefs.equals(other.userPrefs)
                && filteredCapEntriesList.equals(other.filteredCapEntriesList)
                && Objects.equals(selectedCapEntry.get(), other.selectedCapEntry.get());
    }

}
