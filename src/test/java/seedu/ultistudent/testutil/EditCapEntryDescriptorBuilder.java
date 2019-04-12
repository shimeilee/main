package seedu.ultistudent.testutil;

import seedu.ultistudent.logic.commands.EditCapEntryCommand;
import seedu.ultistudent.logic.commands.EditCapEntryCommand.EditCapEntryDescriptor;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * A utility class to help with building EditCapEntryDescriptor objects.
 */
public class EditCapEntryDescriptorBuilder {

    private EditCapEntryDescriptor descriptor;

    public EditCapEntryDescriptorBuilder() {
        descriptor = new EditCapEntryCommand.EditCapEntryDescriptor();
    }

    public EditCapEntryDescriptorBuilder(EditCapEntryDescriptor descriptor) {
        this.descriptor = new EditCapEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCapEntryDescriptor} with fields containing {@code capEntry}'s details
     */
    public EditCapEntryDescriptorBuilder(CapEntry capEntry) {
        descriptor = new EditCapEntryDescriptor();
        descriptor.setModuleCode(capEntry.getModuleCode());
        descriptor.setModuleGrade(capEntry.getModuleGrade());
        descriptor.setModuleCredits(capEntry.getModuleCredits());
        descriptor.setModuleSemester(capEntry.getModuleSemester());
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditCapEntryDescriptor} that we are building.
     */
    public EditCapEntryDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code ModuleGrade} of the {@code EditCapEntryDescriptor} that we are building.
     */
    public EditCapEntryDescriptorBuilder withModuleGrade(String moduleGrade) {
        descriptor.setModuleGrade(new ModuleGrade(moduleGrade));
        return this;
    }

    /**
     * Sets the {@code ModuleCredits} of the {@code EditCapEntryDescriptor} that we are building.
     */
    public EditCapEntryDescriptorBuilder withModuleCredits(String moduleCredits) {
        descriptor.setModuleCredits(new ModuleCredits(moduleCredits));
        return this;
    }

    /**
     * Sets the {@code ModuleSemester} of the {@code EditCapEntryDescriptor} that we are building.
     */
    public EditCapEntryDescriptorBuilder withModuleSemester(String moduleSemester) {
        descriptor.setModuleSemester(new ModuleSemester(moduleSemester));
        return this;
    }

    public EditCapEntryDescriptor build() {
        return descriptor;
    }
}
