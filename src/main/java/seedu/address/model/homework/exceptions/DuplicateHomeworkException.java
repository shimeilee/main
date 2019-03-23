package seedu.address.model.homework.exceptions;

/**
 * Signals that the operation will result in duplicate Cap Entries (Cap Entries are considered duplicates if they have
 * the same identity).
 */
public class DuplicateHomeworkException extends RuntimeException {
    public DuplicateHomeworkException() {
        super("Operation would result in duplicate homework");
    }
}
