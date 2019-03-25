package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;

import seedu.address.model.tag.Tag;

/**
 * Represents a Note in UltiStudent - Notes Keeping.
 * Uses the Cornell method to help students write better notes in class.
 * Cornell method consists of Keywords, Questions, and Summary.
 */
public class Note {

    // Notes fields
    private ModuleCode moduleCode;
    private NoteName noteName;
    private Tag tag;

     // Data fields
    private File content;

    /**
     * Every field must be present and not null.
     */
    public Note(ModuleCode moduleCode, NoteName noteName, Tag tag) {
        requireAllNonNull(moduleCode, noteName);
        this.moduleCode = moduleCode;
        this.noteName = noteName;
        this.tag = tag;
        this.content = new File();
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public NoteName getNoteName() {
        return noteName;
    }

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
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && moduleCode.equals(((Note) other).moduleCode) // state check
                && noteName.equals(((Note) other).noteName)); // state check
    }

    @Override
    public int hashCode() {
        return noteName.hashCode();
    }
}
