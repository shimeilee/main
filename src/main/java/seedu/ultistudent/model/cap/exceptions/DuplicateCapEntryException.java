package seedu.ultistudent.model.cap.exceptions;

/**
 * Signals that the operation will result in duplicate cap entries (Cap entries are considered duplicates if they have
 * the same identity).
 */
public class DuplicateCapEntryException extends RuntimeException {
    public DuplicateCapEntryException() {
        super("Operation would result in duplicate cap entries");
    }
}
