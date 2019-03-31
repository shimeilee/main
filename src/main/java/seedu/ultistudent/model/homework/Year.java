package seedu.ultistudent.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's year which is used to denote deadlines for a Homework.
 */
public class Year {
    public static final String MESSAGE_CONSTRAINTS =
            "Year should only contain numbers, and should be 4 digits long.";
    public static final String VALIDATION_REGEX = "\\d{4}";
    public final String value;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid year number.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid year number.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && value.equals(((Year) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
