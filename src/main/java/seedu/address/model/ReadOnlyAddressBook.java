package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.homework.Homework;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an UltiStudent
 */
public interface ReadOnlyAddressBook extends Observable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the cap entries list.
     * This list will not contain any duplicate cap entries.
     */
    ObservableList<CapEntry> getCapEntryList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Homework> getHomeworkList();

}
