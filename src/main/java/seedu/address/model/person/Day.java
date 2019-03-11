package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's day which is used to denote deadlines for a Homework.
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain a number from 1-31";
    //public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day) {
        requireNonNull(day);
        //checkArgument(isValidDay(phone), MESSAGE_CONSTRAINTS);
        value = day;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDay(String test) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
