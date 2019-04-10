package seedu.ultistudent.model.cap;

import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.ultistudent.model.modulecode.ModuleCode;

/** Represents a cap entry for the cap manager in the Ultistudent.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class CapEntry {

    // Static fields
    private static double capScore = 0;
    private static double totalScore = 0;
    private static double totalModuleCredits = 0;
    // Identity fields
    private ModuleCode moduleCode;
    private ModuleGrade moduleGrade;
    private ModuleCredits moduleCredits;
    private ModuleSemester moduleSemester;

    /**
     * Every field must be present and not null.
     */
    public CapEntry(ModuleCode moduleCode, ModuleGrade moduleGrade, ModuleCredits moduleCredits,
                    ModuleSemester moduleSemester) {
        requireAllNonNull(moduleCode, moduleGrade, moduleCredits, moduleSemester);
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
    }

    /**
     * Takes in a valid cap entry that is to be added to the uniqueCapEntryList and updates the capScore
     * {@code capEntryToAdd} must be valid.
     */
    public static void updateCapForAdd(CapEntry capEntryToAdd) {
        ModuleGrade moduleGrade = capEntryToAdd.getModuleGrade();
        ModuleCredits moduleCredits = capEntryToAdd.getModuleCredits();
        totalScore += moduleGrade.getScore() * moduleCredits.getValue();
        totalModuleCredits += moduleCredits.getValue();
        capScore = totalScore / totalModuleCredits;
    }

    /**
     * Takes in a valid cap entry that is to be deleted from the uniqueCapEntryList and updates the capScore
     * {@code capEntryToDelete} must exists.
     */
    public static void updateCapForDelete(CapEntry capEntryToDelete) {
        ModuleGrade moduleGrade = capEntryToDelete.getModuleGrade();
        ModuleCredits moduleCredits = capEntryToDelete.getModuleCredits();
        totalScore -= moduleGrade.getScore() * moduleCredits.getValue();
        totalModuleCredits -= moduleCredits.getValue();
        capScore = totalScore / totalModuleCredits;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public ModuleGrade getModuleGrade() {
        return moduleGrade;
    }

    public ModuleCredits getModuleCredits() {
        return moduleCredits;
    }

    public ModuleSemester getModuleSemester() {
        return moduleSemester;
    }

    public static String getCapScore() {
        if (Double.isNaN(capScore)) {
            capScore = 0;
        }
        return String.format("%.2f", capScore) + "";
    }

    /**
     * Returns true if both cap entries are of the same module code.
     * This defines a weaker notion of equality between two cap entries.
     */
    public boolean isSameCapEntry(CapEntry otherCapEntry) {
        if (otherCapEntry == this) {
            return true;
        }

        return otherCapEntry != null
                && otherCapEntry.getModuleCode().equals(getModuleCode());
//                && (otherCapEntry.getModuleGrade().equals(getModuleGrade())
//                || otherCapEntry.getModuleCredits().equals(getModuleGrade()));
    }

    /**
     * Returns true if both cap entries have the same identity and data fields.
     * This defines a stronger notion of equality between two cap entries.
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
                && otherCapEntry.getModuleCredits().equals(getModuleCredits())
                && otherCapEntry.getModuleSemester().equals(getModuleSemester());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(moduleCode)
                .append("; ")
                .append(moduleGrade)
                .append("; ")
                .append(moduleCredits)
                .append("MC; ")
                .append(moduleSemester);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, moduleGrade, moduleCredits, moduleSemester);
    }
}
