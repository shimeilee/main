package seedu.address.model.cap;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/** Represents a CAP entry for the CAP calculator in the address book.
 * Guarantees: WIP.
 */

public class CapEntry {

    public static final String MESSAGE_CONSTRAINTS = "Entries must beAddresses can take any values, "
            + "and it should not be blank";

    // Identity fields
    private String moduleCode;
    private String grade;
    private int modularCredits;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public CapEntry(String moduleCode, String grade, int modularCredits) {
        requireAllNonNull(moduleCode, grade, modularCredits);
        this.moduleCode = moduleCode;
        this.grade = grade;
        this.modularCredits = modularCredits;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleGrade() {
        return grade;
    }

    public int getModularCredits() {
        return modularCredits;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both cap entries of the same module code have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCapEntry(CapEntry otherCapEntry) {
        if (otherCapEntry == this) {
            return true;
        }

        return otherCapEntry != null
                && otherCapEntry.getModuleCode().equals(getModuleCode())
                && (otherCapEntry.getModuleGrade().equals(getModuleGrade())
                || otherCapEntry.getModularCredits() == (getModularCredits()));
    }

    /**
     * Returns true if both capEntry have the same identity and data fields.
     * This defines a stronger notion of equality between two capEntry.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CapEntry)) {
            return false;
        }

        CapEntry otherCapEntry = (CapEntry) other;
        return otherCapEntry.getModuleCode().equals(getModuleCode())
                && otherCapEntry.getModuleGrade().equals(getModuleGrade())
                && otherCapEntry.getModularCredits() == (getModularCredits())
                && otherCapEntry.getTags().equals(getTags());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(moduleCode)
                .append("; ")
                .append(grade)
                .append("; ")
                .append(modularCredits);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, grade, modularCredits);
    }
}
