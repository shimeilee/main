package seedu.address.model.homework;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Date for a deadline in a homework entry in UltiStudent.
 */
public class Date {
    // Identity fields
    private final Day day;
    private final Month month;
    private final Year year;

    /**
     * Every field must be present and not null.
     */
    public Date(Day day, Month month, Year year) {
        requireAllNonNull(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Day getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    /**
     * Returns true if both homeworks have the same deadline.
     */
    public boolean isSameDate(Date otherDate) {
        if (otherDate == this) {
            return true;
        }

        return otherDate != null
                && otherDate.getDay().equals(getDay())
                && (otherDate.getMonth().equals(getMonth()) && otherDate.getYear().equals(getYear()));
    }

    /**
     * Returns true if both persons have the same deadline.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Date otherDate = (Date) other;
        return otherDate.getDay().equals(getDay())
                && otherDate.getMonth().equals(getMonth())
                && otherDate.getYear().equals(getYear());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDay())
                .append(" ")
                .append(getMonth())
                .append(" ")
                .append(getYear());

        return builder.toString();
    }
}
