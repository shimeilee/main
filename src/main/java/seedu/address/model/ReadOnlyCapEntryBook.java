package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.cap.CapEntry;

/**
 * Unmodifiable view of an CapEntry
 */
public interface ReadOnlyCapEntryBook extends Observable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<CapEntry> getCapEntryList();

}
