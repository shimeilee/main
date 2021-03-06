package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;

/**
 * Represents a cap entry's modular semester in the UltiStudent.
 */
public class ModuleSemester {

    public static final String MESSAGE_CONSTRAINTS = "Modular Semester should be entered in Y#S@ format,"
            + " where # is a whole number from 1 to 5 and @ is from 1 to 2";
    public static final String VALIDATION_REGEX = "[Y][1-5][S][1-2]";
    public final String value;

    /**
     * Constructs a {@code ModuleSemester}.
     * @param moduleSemester a valid moduleSemester
     */
    public ModuleSemester (String moduleSemester) {
        requireNonNull(moduleSemester);
        value = moduleSemester.toUpperCase();
    }

    /**
     * Returns true if it is a valid ModuleSemester
     */
    public static boolean isValidModuleSemester(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of the ModuleSemester.
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleSemester // instanceof handles nulls
                && value.equals(((ModuleSemester) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

