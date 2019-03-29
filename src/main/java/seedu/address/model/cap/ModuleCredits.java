package seedu.address.model.cap;

import static java.util.Objects.requireNonNull;

/**
 * Represents a cap entry's modular credits in the UltiStudent.
 */
public class ModuleCredits {

    public static final String MESSAGE_CONSTRAINTS = "Modular credits should be positive whole numbers of 1 or " +
            "2 digits";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final String value;

    /**
     * Constructs a {@code ModuleCredits}.
     * @param moduleCredits
     */
    public ModuleCredits (String moduleCredits) {
        requireNonNull(moduleCredits);
        value = moduleCredits;
    }

    /**
     * Returns true if it is a valid ModuleCode
     */
    public static boolean isValidModuleCredits(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if it is a valid ModuleCode
     */
    public int getValue() {
        return Integer.parseInt(this.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCredits // instanceof handles nulls
                && value.equals(((ModuleCredits) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
