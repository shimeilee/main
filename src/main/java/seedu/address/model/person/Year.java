package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's year which is used to denote deadlines for a Homework.
 */
public class Year {
    public static final String MESSAGE_CONSTRAINTS =
            "Year should only contain numbers, and should be 4 digits long.";
    //public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Year(String year) {
        requireNonNull(year);
        //checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return true;
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
