package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

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

    // Data fields
    private Content content;
    private Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Note(ModuleCode moduleCode, NoteName noteName, Tag tag, Content
            content) {
        requireAllNonNull(moduleCode, noteName, content);
        this.moduleCode = moduleCode;
        this.noteName = noteName;
        this.tag = tag;
        this.content = content;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public NoteName getNoteName() {
        return noteName;
    }

    public Content getContent() {
        return content;
    }

    /**
     * Returns true if both notes of the same name have at least one other
     * identity field that is the same.
     * This defines a weaker notion of equality between two notes.
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
        builder.append(" Module: ")
                .append(getModuleCode())
                .append(" Note: ")
                .append(getNoteName())
                .append(" Content: ")
                .append(getContent());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote.getModuleCode().equals(getModuleCode())
                && otherNote.getNoteName().equals(getNoteName())
                && otherNote.getContent().equals(getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, noteName, content);
    }
}
