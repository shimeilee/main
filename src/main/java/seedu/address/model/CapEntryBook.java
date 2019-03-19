package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.cap.UniqueCapEntryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */

public class CapEntryBook implements ReadOnlyCapEntryBook {

    private final UniqueCapEntryList capEntryList;
    private final InvalidationListenerManager capEntryInvalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        capEntryList = new UniqueCapEntryList();
    }

    public CapEntryBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public CapEntryBook(ReadOnlyCapEntryBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code capEntryList}.
     * {@code capEntryList} must not contain duplicate capEntry.
     */
    public void setCapEntryList(List<CapEntry> capEntryList) {
        this.capEntryList.setCapEntryList(capEntryList);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyCapEntryBook newData) {
        requireNonNull(newData);

        setCapEntryList(newData.getCapEntryList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UltiStudent.
     */
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return capEntryList.contains(capEntry);
    }

    /**
     * Adds a person to the UltiStudent.
     * The person must not already exist in the UltiStudent.
     */
    public void addCapEntry(CapEntry c) {
        capEntryList.add(c);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the UltiStudent.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UltiStudent.
     */
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireNonNull(editedCapEntry);

        capEntryList.setCapEntry(target, editedCapEntry);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removeCapEntry(CapEntry key) {
        capEntryList.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        capEntryInvalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        capEntryInvalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the UltiStudent has been modified.
     */
    protected void indicateModified() {
        capEntryInvalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return capEntryList.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<CapEntry> getCapEntryList() {
        return capEntryList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CapEntryBook // instanceof handles nulls
                && capEntryList.equals(((CapEntryBook) other).capEntryList));
    }

    @Override
    public int hashCode() {
        return capEntryList.hashCode();
    }
}

