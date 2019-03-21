package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Homework's modulecode in the UltiStudent.
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should begin with at least two capital letters "
            + "followed by at least four letters. May end with an optional capital letter at the back";
    public static final String VALIDATION_REGEX = "([a-zA-Z]{2,3})(\\d{4})([a-zA-Z]{1})?";
    public final String value;

    /**
     * Constructs a {@code ModuleCode}.
     * @param moduleCode
     */
    public ModuleCode (String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode;
    }

    //TODO: Regex check for moduleCode
    /**
     * Returns true if it is a valid ModuleCode
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && value.equals(((ModuleCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
