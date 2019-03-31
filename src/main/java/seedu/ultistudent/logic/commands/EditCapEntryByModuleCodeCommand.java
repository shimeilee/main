package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.ultistudent.commons.util.CollectionUtil;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.tag.Tag;

/**
 * Edits the details of an existing cap entry in the Cap manager through the module code.
 */
public class EditCapEntryByModuleCodeCommand extends Command {
    public static final String COMMAND_WORD = "editcap-mc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the module code stored in the displayed cap entry list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: module code (must be valid format) "
            + "[" + PREFIX_MODULECODE + "MODULE_CODE] "
            + "[" + PREFIX_MODULEGRADE + "MODULE_GRADE] "
            + "[" + PREFIX_MODULECREDITS + "MODULE_CREDITS] "
            + "[" + PREFIX_SEMESTER + "MODULE_SEMESTER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " CS2100 "
            + PREFIX_MODULEGRADE + "B+ "
            + PREFIX_MODULECREDITS + "4";

    public static final String MESSAGE_EDIT_CAP_ENTRY_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "This cap entry already exists in the Cap Manager.";

    private final ModuleCode moduleCode;
    private final EditCapEntryDescriptor editCapEntryDescriptor;

    /**
     * @param moduleCode of the person in the filtered person list to edit
     * @param editCapEntryDescriptor details to edit the person with
     */
    public EditCapEntryByModuleCodeCommand(ModuleCode moduleCode, EditCapEntryDescriptor editCapEntryDescriptor) {
        requireNonNull(moduleCode);
        requireNonNull(editCapEntryDescriptor);

        this.moduleCode = moduleCode;
        this.editCapEntryDescriptor = new EditCapEntryDescriptor(editCapEntryDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<CapEntry> lastShownList = model.getFilteredCapEntryList();
        CapEntry capEntryToEdit = null;
        CapEntry editedCapEntry;

        for (int i = 0; i < lastShownList.size(); i++) {
            if (moduleCode.equals(lastShownList.get(i).getModuleCode())) {
                capEntryToEdit = lastShownList.get(i);
            }
        }
        if (capEntryToEdit == null) {
            throw new CommandException(("MODULE NOT FOUND IN CAP MANAGER"));
        }

        editedCapEntry = createEditedCapEntry(capEntryToEdit, editCapEntryDescriptor);

        //        if (moduleCode.getValue() >= lastShownList.size()) {
        //            throw new CommandException(Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
        //        }
        //
        //        CapEntry capEntryToEdit = lastShownList.get(index.getZeroBased());
        //        CapEntry editedCapEntry = createEditedCapEntry(capEntryToEdit, editCapEntryDescriptor);

        if (!capEntryToEdit.isSameCapEntry(editedCapEntry) && model.hasCapEntry(editedCapEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_CAP_ENTRY);
        }

        model.setCapEntry(capEntryToEdit, editedCapEntry);
        model.updateFilteredCapEntryList(Model.PREDICATE_SHOW_ALL_CAP_ENTRIES);
        model.commitAddressBook();
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
        Set<Tag> updatedTags = editCapEntryDescriptor.getTags().orElse(capEntryToEdit.getTags());

        return new CapEntry(updatedModuleCode, updatedModuleGrade, updatedModuleCredits, updatedModuleSemester,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCapEntryByModuleCodeCommand e = (EditCapEntryByModuleCodeCommand) other;
        return moduleCode.equals(e.moduleCode)
                && editCapEntryDescriptor.equals(e.editCapEntryDescriptor);
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
        private Set<Tag> tags;

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
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, moduleGrade, moduleCredits, moduleSemester, tags);
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

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
                    && getModuleSemester().equals(e.getModuleSemester())
                    && getTags().equals(e.getTags());
        }
    }
}
