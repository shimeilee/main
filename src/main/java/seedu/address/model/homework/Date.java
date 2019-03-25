package seedu.address.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Date for a deadline in a homework entry in UltiStudent.
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "The format for the date should follow DD/MM/YYYY.";
    public static final String VALIDATION_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
    // Identity fields
    private String value;

    /**
     * Every field must be present and not null.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.value = date;
    }

    public String getDate () {
        return value;
    }

    /**
     * Returns true if it is a valid date.
     */
    public static boolean isValidDate(String test) {
        System.out.println(test);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both persons have the same deadline.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        return value.equals(((Date) other).value);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
