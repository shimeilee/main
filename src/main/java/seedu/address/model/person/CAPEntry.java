package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a CAP entry for the CAP calculator in the address book.
 * Guarantees: WIP.
 */

public class CAPEntry {

    public static final String MESSAGE_CONSTRAINTS = "Entries must beAddresses can take any values, and it should not be blank";

    private String moduleCode;
    private String Grade;
    private int modularCredits;

    /**
     * Every field must be present and not null.
     */
    public CAPEntry(String moduleCode, String Grade, int modularCredits){
        requireAllNonNull(moduleCode, Grade, modularCredits);
        this.moduleCode = moduleCode;
        this.Grade = Grade;
        this.modularCredits = modularCredits;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CAPEntry // instanceof handles nulls
                && moduleCode.equals(((CAPEntry) other).moduleCode)
                && Grade.equals(((CAPEntry) other).Grade)
                && modularCredits == ((CAPEntry) other).modularCredits); // state check
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(moduleCode)
                .append("; ")
                .append(Grade)
                .append("; ")
                .append(modularCredits);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, Grade, modularCredits);
    }
}
