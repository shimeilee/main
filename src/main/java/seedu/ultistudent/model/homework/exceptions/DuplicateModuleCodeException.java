package seedu.ultistudent.model.homework.exceptions;

/**
 * Signals that the operation will result in duplicate module code(Module Codes are considered duplicates if they have
 * the same identity).
 */
public class DuplicateModuleCodeException extends RuntimeException {
    public DuplicateModuleCodeException() {
        super("Operation would result in duplicate module code");
    }
}
