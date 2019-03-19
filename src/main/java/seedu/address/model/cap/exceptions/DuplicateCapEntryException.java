package seedu.address.model.cap.exceptions;

/**
 * Signals that the operation will result in duplicate Cap Entries (Cap Entries are considered duplicates if they have
 * the same identity).
 */
public class DuplicateCapEntryException extends RuntimeException {
    public DuplicateCapEntryException() {
        super("Operation would result in duplicate cap entries");
    }
}
