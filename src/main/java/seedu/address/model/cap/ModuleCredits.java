package seedu.address.model.cap;

import static java.util.Objects.requireNonNull;

/**
 * Represents a cap entry's modular credits in the UltiStudent.
 */
public class ModuleCredits {

    public static final String MESSAGE_CONSTRAINTS = "Modular credits should be positive whole numbers ";
    public static final String VALIDATION_REGEX = "\\d{1}";
    public final String value;

    /**
     * Constructs a {@code ModuleCredits}.
     * @param moduleCredits
     */
    public ModuleCredits (String moduleCredits) {
        requireNonNull(moduleCredits);
        value = moduleCredits;
    }

    //TODO: Regex check for modulecode
    /**
     * Returns true if it is a valid ModuleCode
     */
    public static boolean isValidModuleCredits(String test) {
        return true;
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
