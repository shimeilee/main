package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Note in UltiStudent - Notes Keeping.
 */
public class Note {

    // Notes fields
    private String modulename;
    private String notename;

    // Data fields
    // private File content;

    /**
     * Every field must be present and not null.
     */
    public Note(String moduleName, String noteName) {
        requireAllNonNull(moduleName, noteName);
        modulename = moduleName;
        notename = noteName;
        // content = new File(new );
    }

    @Override
    /**
     * Returns a String containing the note name.
     */
    public String toString() {
        return modulename + " " + notename;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && modulename.equals(((Note) other).modulename) // state check
                && notename.equals(((Note) other).notename)); // state check
    }

    @Override
    public int hashCode() { return notename.hashCode(); }
}
