package seedu.ultistudent.model.homework.exceptions;

/**
 * Signals that the operation will result in duplicate Homework (Homework are considered duplicates if they have
 * the same identity).
 */
public class DuplicateHomeworkException extends RuntimeException {
    public DuplicateHomeworkException() {
        super("Operation would result in duplicate homework");
    }
}
