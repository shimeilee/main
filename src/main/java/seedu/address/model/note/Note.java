package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Note in UltiStudent - Notes Keeping.
 */
public class Note {

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO: change to validate according to input string
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    // Notes fields
    private String moduleName;
    private String noteName;

    // Data fields
    // private File content;
    // AL of AL

    /**
     * Every field must be present and not null.
     */
    public Note(String moduleName, String noteName) {
        requireAllNonNull(moduleName, noteName);
        this.moduleName = moduleName;
        this.noteName = noteName;
        // content = new File(new );
    }

    /**
    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
     **/

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * Returns a String containing the note name.
     */
    public String toString() {
        return moduleName + " " + noteName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && moduleName.equals(((Note) other).moduleName) // state check
                && noteName.equals(((Note) other).noteName)); // state check
    }

    @Override
    public int hashCode() {
        return noteName.hashCode();
    }
}
