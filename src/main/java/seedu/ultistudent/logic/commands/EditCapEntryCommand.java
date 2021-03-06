package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_SEMESTER;

import java.util.List;
import java.util.Optional;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.commons.util.CollectionUtil;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Edits the details of an existing cap entry in the Cap Manager through the index.
 */
public class EditCapEntryCommand extends Command {
    public static final String COMMAND_WORD = "edit-cap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the index number used in the displayed cap entry list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULECODE + "MODULE_CODEN] "
            + "[" + PREFIX_MODULEGRADE + "MODULE_GRADE] "
            + "[" + PREFIX_MODULECREDITS + "MODULE_CREDITS] "
            + "[" + PREFIX_SEMESTER + "MODULE_SEMESTER]. \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULEGRADE + "B+ "
            + PREFIX_MODULECREDITS + "6";

    public static final String MESSAGE_EDIT_CAP_ENTRY_SUCCESS = "Edited Cap Entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "This cap entry already exists in the Cap Manager.";

    private final Index index;
    private final EditCapEntryDescriptor editCapEntryDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editCapEntryDescriptor details to edit the person with
     */
    public EditCapEntryCommand(Index index, EditCapEntryDescriptor editCapEntryDescriptor) {
        requireNonNull(index);
        requireNonNull(editCapEntryDescriptor);

        this.index = index;
        this.editCapEntryDescriptor = new EditCapEntryDescriptor(editCapEntryDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<CapEntry> lastShownList = model.getFilteredCapEntryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
        }

        CapEntry capEntryToEdit = lastShownList.get(index.getZeroBased());
        CapEntry editedCapEntry = createEditedCapEntry(capEntryToEdit, editCapEntryDescriptor);
        ModuleSemester moduleSemesterOfCapEntryToEdit = capEntryToEdit.getModuleSemester();
        ModuleSemester moduleSemesterOfEditedCapEntry = editedCapEntry.getModuleSemester();

        if (!capEntryToEdit.isSameCapEntry(editedCapEntry) && model.hasCapEntry(editedCapEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_CAP_ENTRY);
        }

        model.setCapEntry(capEntryToEdit, editedCapEntry);
        model.updateFilteredCapEntryList(Model.PREDICATE_SHOW_ALL_CAP_ENTRIES);

        //update module semester list
        if (!moduleSemesterOfCapEntryToEdit.equals(moduleSemesterOfEditedCapEntry)) {
            if (!model.hasModuleSemester(moduleSemesterOfEditedCapEntry)) {
                model.addModuleSemester(moduleSemesterOfEditedCapEntry);
            }

            boolean hasCapEntriesWithSameSemester = false;
            List<CapEntry> afterEditList = model.getFilteredCapEntryList();
            hasCapEntriesWithSameSemester = checkForModuleSemester(moduleSemesterOfCapEntryToEdit, afterEditList);

            if (hasCapEntriesWithSameSemester == false) {
                model.deleteModuleSemester(moduleSemesterOfCapEntryToEdit);
            }
        }

        model.updateFilteredModuleSemesterList(Model.PREDICATE_SHOW_ALL_MODULE_SEMESTERS);

        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_EDIT_CAP_ENTRY_SUCCESS, editedCapEntry));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static CapEntry createEditedCapEntry(CapEntry capEntryToEdit,
                                                 EditCapEntryDescriptor editCapEntryDescriptor) {
        assert capEntryToEdit != null;

        ModuleCode updatedModuleCode = editCapEntryDescriptor.getModuleCode().orElse(capEntryToEdit.getModuleCode());
        ModuleGrade updatedModuleGrade = editCapEntryDescriptor.getModuleGrade().orElse(capEntryToEdit
                .getModuleGrade());
        ModuleCredits updatedModuleCredits = editCapEntryDescriptor.getModuleCredits().orElse(capEntryToEdit
                .getModuleCredits());
        ModuleSemester updatedModuleSemester = editCapEntryDescriptor.getModuleSemester().orElse(capEntryToEdit
                .getModuleSemester());

        return new CapEntry(updatedModuleCode, updatedModuleGrade, updatedModuleCredits, updatedModuleSemester);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCapEntryCommand)) {
            return false;
        }

        // state check
        EditCapEntryCommand e = (EditCapEntryCommand) other;
        return index.equals(e.index)
                && editCapEntryDescriptor.equals(e.editCapEntryDescriptor);
    }

    /**
     * Checks the contents of {@code capEntryList} if the list contains any cap entries with the module semester of
     * {@code moduleSemester}.
     */
    private boolean checkForModuleSemester(ModuleSemester moduleSemester, List<CapEntry> capEntryList) {
        boolean hasCapEntriesWithSameSemester = false;
        for (int i = 0; i < capEntryList.size(); i++) {
            if (capEntryList.get(i).getModuleSemester().equals(moduleSemester)) {
                hasCapEntriesWithSameSemester = true;
            }
        }
        return hasCapEntriesWithSameSemester;
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCapEntryDescriptor {
        private ModuleCode moduleCode;
        private ModuleGrade moduleGrade;
        private ModuleCredits moduleCredits;
        private ModuleSemester moduleSemester;

        public EditCapEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCapEntryDescriptor(EditCapEntryDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setModuleGrade(toCopy.moduleGrade);
            setModuleCredits(toCopy.moduleCredits);
            setModuleSemester(toCopy.moduleSemester);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, moduleGrade, moduleCredits, moduleSemester);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleGrade(ModuleGrade moduleGrade) {
            this.moduleGrade = moduleGrade;
        }

        public Optional<ModuleGrade> getModuleGrade() {
            return Optional.ofNullable(moduleGrade);
        }

        public void setModuleCredits(ModuleCredits moduleCredits) {
            this.moduleCredits = moduleCredits;
        }

        public Optional<ModuleCredits> getModuleCredits() {
            return Optional.ofNullable(moduleCredits);
        }

        public void setModuleSemester(ModuleSemester moduleSemester) {
            this.moduleSemester = moduleSemester;
        }

        public Optional<ModuleSemester> getModuleSemester() {
            return Optional.ofNullable(moduleSemester);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCapEntryDescriptor)) {
                return false;
            }

            // state check
            EditCapEntryDescriptor e = (EditCapEntryDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                    && getModuleGrade().equals(e.getModuleGrade())
                    && getModuleCredits().equals(e.getModuleCredits())
                    && getModuleSemester().equals(e.getModuleSemester());
        }
    }
}
