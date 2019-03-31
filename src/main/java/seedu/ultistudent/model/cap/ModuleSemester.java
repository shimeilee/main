package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;

/**
 * Represents a cap entry's modular credits in the UltiStudent.
 */
public class ModuleSemester {

    public static final String MESSAGE_CONSTRAINTS = "Modular Semester should be entered in Y#S# format,"
            + " where # are whole numbers ";
    public static final String VALIDATION_REGEX = "(Y)\\d{1}(S)\\d{1}";
    public final String value;

    /**
     * Constructs a {@code ModuleCredits}.
     * @param moduleSemester
     */
    public ModuleSemester (String moduleSemester) {
        requireNonNull(moduleSemester);
        value = moduleSemester.toUpperCase();
    }

    /**
     * Returns true if it is a valid ModuleCode
     */
    public static boolean isValidModuleSemester(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if it is a valid ModuleCode
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

