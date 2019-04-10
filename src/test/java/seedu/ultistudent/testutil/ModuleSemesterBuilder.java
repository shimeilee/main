package seedu.ultistudent.testutil;

import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * A utility class to help with building ModuleSemester objects.
 */
public class ModuleSemesterBuilder {

    public static final String DEFAULT_MODULE_SEMESTER = "Y1S2";

    private String moduleSemester;

    public ModuleSemesterBuilder() {
        moduleSemester = DEFAULT_MODULE_SEMESTER;
    }

    /**
     * Initializes the ModuleSemesterBuilder with the data of {@code moduleSemesterToCopy}.
     */
    public ModuleSemesterBuilder(ModuleSemester moduleSemesterToCopy) {
        moduleSemester = moduleSemesterToCopy.getValue();
    }

    /**
     * Sets the {@code ModuleSemester} of the {@code CapEntry} that we are building.
     */
    public ModuleSemesterBuilder withModuleSemester(String moduleSemester) {
        this.moduleSemester = moduleSemester;
        return this;
    }

    public ModuleSemester build() {
        return new ModuleSemester(moduleSemester);
    }

}
