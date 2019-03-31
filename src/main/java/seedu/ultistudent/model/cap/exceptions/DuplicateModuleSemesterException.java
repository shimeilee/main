package seedu.ultistudent.model.cap.exceptions;

/**
 * Signals that the operation will result in duplicate Module Semester (Module Semesters are considered duplicates if
 * they have the same identity).
 */
public class DuplicateModuleSemesterException extends RuntimeException {
    public DuplicateModuleSemesterException() {
        super("Operation would result in duplicate module semesters");
    }
}
