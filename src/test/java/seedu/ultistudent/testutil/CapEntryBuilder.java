package seedu.ultistudent.testutil;

import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * A utility class to help with building CapEntry objects.
 */
public class CapEntryBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_MODULE_GRADE = "B+";
    public static final String DEFAULT_MODULE_CREDITS = "4";
    public static final String DEFAULT_MODULE_SEMESTER = "Y2S2";

    private ModuleCode moduleCode;
    private ModuleGrade moduleGrade;
    private ModuleCredits moduleCredits;
    private ModuleSemester moduleSemester;

    public CapEntryBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleGrade = new ModuleGrade(DEFAULT_MODULE_GRADE);
        moduleCredits = new ModuleCredits(DEFAULT_MODULE_CREDITS);
        moduleSemester = new ModuleSemester(DEFAULT_MODULE_SEMESTER);
    }

    /**
     * Initializes the CapEntryBuilder with the data of {@code capEntryToCopy}.
     */
    public CapEntryBuilder(CapEntry capEntryToCopy) {
        moduleCode = capEntryToCopy.getModuleCode();
        moduleGrade = capEntryToCopy.getModuleGrade();
        moduleCredits = capEntryToCopy.getModuleCredits();
        moduleSemester = capEntryToCopy.getModuleSemester();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code CapEntry} that we are building.
     */
    public CapEntryBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code ModuleSemester} of the {@code CapEntry} that we are building.
     */
    public CapEntryBuilder withModuleSemester(String moduleSemester) {
        this.moduleSemester = new ModuleSemester(moduleSemester);
        return this;
    }

    /**
     * Sets the {@code ModuleGrade} of the {@code CapEntry} that we are building.
     */
    public CapEntryBuilder withModuleGrade(String moduleGrade) {
        this.moduleGrade = new ModuleGrade(moduleGrade);
        return this;
    }

    /**
     * Sets the {@code ModuleCredits} of the {@code CapEntry} that we are building.
     */
    public CapEntryBuilder withModuleCredits(String moduleCredits) {
        this.moduleCredits = new ModuleCredits(moduleCredits);
        return this;
    }

    public CapEntry build() {
        return new CapEntry(moduleCode, moduleGrade, moduleCredits, moduleSemester);
    }

}
