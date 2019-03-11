package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

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
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
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
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
