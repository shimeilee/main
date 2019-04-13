package seedu.ultistudent.model;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.cap.exceptions.CapEntryNotFoundException;
import seedu.ultistudent.model.cap.exceptions.ModuleSemesterNotFoundException;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.exceptions.HomeworkNotFoundException;
import seedu.ultistudent.model.homework.exceptions.ModuleCodeNotFoundException;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.exceptions.NoteNotFoundException;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the UltiStudent data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedUltiStudent versionedUltiStudent;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();
    private final FilteredList<CapEntry> filteredCapEntryList;
    private final SimpleObjectProperty<CapEntry> selectedCapEntry = new SimpleObjectProperty<>();
    private final FilteredList<Homework> filteredHomeworkList;
    private final SimpleObjectProperty<Homework> selectedHomework = new SimpleObjectProperty<>();
    private final FilteredList<Note> filteredNoteList;
    private final SimpleObjectProperty<Note> selectedNote = new SimpleObjectProperty<>();
    private final FilteredList<ModuleSemester> filteredModuleSemesterList;
    private final SimpleObjectProperty<ModuleSemester> selectedModuleSemester = new SimpleObjectProperty<>();
    private final FilteredList<ModuleCode> filteredModuleCodeList;
    private final SimpleObjectProperty<ModuleCode> selectedModuleCode = new SimpleObjectProperty<>();


    /**
     * Initializes a ModelManager with the given ultiStudent and userPrefs.
     */
    public ModelManager(ReadOnlyUltiStudent ultiStudent, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(ultiStudent, userPrefs);

        logger.fine("Initializing with UltiStudent: " + ultiStudent + " and user prefs " + userPrefs);

        versionedUltiStudent = new VersionedUltiStudent(ultiStudent);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedUltiStudent.getPersonList());
        filteredPersons.addListener(this::ensureSelectedPersonIsValid);
        filteredCapEntryList = new FilteredList<>(versionedUltiStudent.getCapEntryList());
        filteredCapEntryList.addListener(this::ensureSelectedCapEntryIsValid);
        filteredHomeworkList = new FilteredList<>(versionedUltiStudent.getHomeworkList());
        filteredHomeworkList.addListener(this::ensureSelectedHomeworkIsValid);
        filteredNoteList = new FilteredList<>(versionedUltiStudent.getNoteList());
        filteredNoteList.addListener(this::ensureSelectedNoteIsValid);
        filteredModuleSemesterList = new FilteredList<>(versionedUltiStudent.getModuleSemesterList());
        filteredModuleSemesterList.addListener(this::ensureSelectedModuleSemesterIsValid);
        filteredModuleCodeList = new FilteredList<>(versionedUltiStudent.getModuleCodeList());
        filteredModuleCodeList.addListener(this::ensureSelectedModuleCodeIsValid);
    }

    public ModelManager() {
        this(new UltiStudent(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUltiStudentFilePath() {
        return userPrefs.getUltiStudentFilePath();
    }

    @Override
    public void setUltiStudentFilePath(Path ultiStudentFilePath) {
        requireNonNull(ultiStudentFilePath);
        userPrefs.setUltiStudentFilePath(ultiStudentFilePath);
    }

    //=========== UltiStudent ================================================================================

    @Override
    public void setUltiStudent(ReadOnlyUltiStudent ultiStudent) {
        versionedUltiStudent.resetData(ultiStudent);
    }

    @Override
    public ReadOnlyUltiStudent getUltiStudent() {
        return versionedUltiStudent;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedUltiStudent.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedUltiStudent.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedUltiStudent.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedUltiStudent.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedUltiStudent}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoUltiStudent() {
        return versionedUltiStudent.canUndo();
    }

    @Override
    public boolean canRedoUltiStudent() {
        return versionedUltiStudent.canRedo();
    }

    @Override
    public void undoUltiStudent() {
        versionedUltiStudent.undo();
    }

    @Override
    public void redoUltiStudent() {
        versionedUltiStudent.redo();
    }

    @Override
    public void commitUltiStudent() {
        versionedUltiStudent.commit();
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(person);
    }


    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends
            Person> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    //====================== CapManager =====================================================================

    @Override
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return versionedUltiStudent.hasCapEntry(capEntry);
    }

    @Override
    public void deleteCapEntry(CapEntry target) {
        versionedUltiStudent.removeCapEntry(target);
    }

    @Override
    public void addCapEntry(CapEntry capEntry) {
        versionedUltiStudent.addCapEntry(capEntry);
        updateFilteredCapEntryList(PREDICATE_SHOW_ALL_CAP_ENTRIES);
    }

    @Override
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireAllNonNull(target, editedCapEntry);

        versionedUltiStudent.setCapEntry(target, editedCapEntry);
    }

    /**
     * Returns an unmodifiable view of the list of {@code CapEntry} backed by the internal list of
     * {@code versionedUltiStudent}
     */
    @Override
    public ObservableList<CapEntry> getFilteredCapEntryList() {
        return filteredCapEntryList;
    }

    @Override
    public void updateFilteredCapEntryList(Predicate<CapEntry> predicate) {
        requireNonNull(predicate);
        filteredCapEntryList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
        return selectedCapEntry;
    }

    @Override
    public CapEntry getSelectedCapEntry() {
        return selectedCapEntry.getValue();
    }

    @Override
    public void setSelectedCapEntry(CapEntry capEntry) {
        if (capEntry != null && !filteredCapEntryList.contains(capEntry)) {
            throw new CapEntryNotFoundException();
        }
        selectedCapEntry.setValue(capEntry);
    }

    /**
     * Ensures {@code selectedCapEntry} is a valid cap entry in {@code filteredCapEntryList}.
     */
    private void ensureSelectedCapEntryIsValid(ListChangeListener.Change<? extends CapEntry> change) {
        while (change.next()) {
            if (selectedCapEntry.getValue() == null) {
                // null is always a valid selected cap entry, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedCapEntryReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedCapEntry.getValue());
            if (wasSelectedCapEntryReplaced) {
                // Update selectedCapEntry to its new value.
                int index = change.getRemoved().indexOf(selectedCapEntry.getValue());
                selectedCapEntry.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedCapEntryRemoved = change.getRemoved().stream()
                    .anyMatch(removedCapEntry -> selectedCapEntry.getValue().isSameCapEntry(removedCapEntry));
            if (wasSelectedCapEntryRemoved) {
                // Select the cap entry that came before it in the list,
                // or clear the selection if there is no such cap entry.
                selectedCapEntry.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }
    //====================== Module Semester List ===========================================================

    @Override
    public boolean hasModuleSemester(ModuleSemester moduleSemester) {
        requireNonNull(moduleSemester);
        return versionedUltiStudent.hasModuleSemester(moduleSemester);
    }

    @Override
    public void deleteModuleSemester(ModuleSemester target) {
        versionedUltiStudent.removeModuleSemester(target);
    }

    @Override
    public void addModuleSemester(ModuleSemester moduleSemester) {
        versionedUltiStudent.addModuleSemester(moduleSemester);
        updateFilteredModuleSemesterList(PREDICATE_SHOW_ALL_MODULE_SEMESTERS);
    }

    @Override
    public void setModuleSemester(ModuleSemester target, ModuleSemester editedModuleSemester) {
        requireAllNonNull(target, editedModuleSemester);

        versionedUltiStudent.setModuleSemester(target, editedModuleSemester);
    }

    /**
     * Returns an unmodifiable view of the list of {@code ModuleSemester} backed by the internal list of
     * {@code versionedUltiStudent}
     */
    @Override
    public ObservableList<ModuleSemester> getFilteredModuleSemesterList() {
        return filteredModuleSemesterList;
    }

    @Override
    public void updateFilteredModuleSemesterList(Predicate<ModuleSemester> predicate) {
        requireNonNull(predicate);
        filteredModuleSemesterList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty() {
        return selectedModuleSemester;
    }

    @Override
    public ModuleSemester getSelectedModuleSemester() {
        return selectedModuleSemester.getValue();
    }

    @Override
    public void setSelectedModuleSemester(ModuleSemester moduleSemester) {
        if (moduleSemester != null && !filteredModuleSemesterList.contains(moduleSemester)) {
            throw new ModuleSemesterNotFoundException();
        }
        selectedModuleSemester.setValue(moduleSemester);
    }

    /**
     * Ensures {@code selectedCapEntry} is a valid cap entry in {@code filteredCapEntryList}.
     */
    private void ensureSelectedModuleSemesterIsValid(ListChangeListener.Change<? extends ModuleSemester> change) {
        while (change.next()) {
            if (selectedModuleSemester.getValue() == null) {
                // null is always a valid selected module semester, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedModuleSemesterReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedModuleSemester.getValue());
            if (wasSelectedModuleSemesterReplaced) {
                // Update selectedModuleSemester to its new value.
                int index = change.getRemoved().indexOf(selectedModuleSemester.getValue());
                selectedModuleSemester.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedModuleSemsterRemoved = change.getRemoved().stream()
                    .anyMatch(removedModuleSemester -> selectedModuleSemester.getValue().equals(removedModuleSemester));
            if (wasSelectedModuleSemsterRemoved) {
                // Select the module semester that came before it in the list,
                // or clear the selection if there is no such module semester.
                selectedModuleSemester.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1)
                        : null);
            }
        }
    }


    //=============================================== HomeworkManager ===============================================//

    @Override
    public boolean hasHomework(Homework homework) {
        requireNonNull(homework);
        return versionedUltiStudent.hasHomework(homework);
    }

    @Override
    public void deleteHomework(Homework target) {
        versionedUltiStudent.removeHomework(target);
    }

    @Override
    public void addHomework(Homework homework) {
        versionedUltiStudent.addHomework(homework);
        updateFilteredHomeworkList(PREDICATE_SHOW_ALL_HOMEWORK);
    }

    @Override
    public void setHomework(Homework target, Homework editedHomework) {
        requireAllNonNull(target, editedHomework);

        versionedUltiStudent.setHomework(target, editedHomework);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedUltiStudent}
     */
    @Override
    public ObservableList<Homework> getFilteredHomeworkList() {
        return filteredHomeworkList;
    }

    @Override
    public void updateFilteredHomeworkList(Predicate<Homework> predicate) {
        requireNonNull(predicate);
        filteredHomeworkList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<Homework> selectedHomeworkProperty() {
        return selectedHomework;
    }

    @Override
    public Homework getSelectedHomework() {
        return selectedHomework.getValue();
    }

    @Override
    public void setSelectedHomework(Homework homework) {
        if (homework != null && !filteredHomeworkList.contains(homework)) {
            throw new HomeworkNotFoundException();
        }
        selectedHomework.setValue(homework);
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedHomeworkIsValid(ListChangeListener.Change<? extends Homework> change) {
        while (change.next()) {
            if (selectedHomework.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedHomeworkReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedHomework.getValue());
            if (wasSelectedHomeworkReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedHomework.getValue());
                selectedHomework.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedHomeworkRemoved = change.getRemoved().stream()
                    .anyMatch(removedHomework -> selectedHomework.getValue().equals(removedHomework));
            if (wasSelectedHomeworkRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedHomework.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    //====================== Module Code List ========================================

    @Override
    public boolean hasModuleCode(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return versionedUltiStudent.hasModuleCode(moduleCode);
    }

    @Override
    public void deleteModuleCode(ModuleCode target) {
        requireNonNull(target);
        versionedUltiStudent.removeModuleCode(target);
    }

    @Override
    public void addModuleCode(ModuleCode moduleCode) {
        versionedUltiStudent.addModuleCode(moduleCode);
        updateFilteredModuleCodeList(PREDICATE_SHOW_ALL_MODULE_CODE);
    }

    @Override
    public void setModuleCode(ModuleCode target, ModuleCode editedModuleCode) {
        requireAllNonNull(target, editedModuleCode);

        versionedUltiStudent.setModuleCode(target, editedModuleCode);
    }

    /**
     * Returns an unmodifiable view of the list of {@code ModuleCode} backed by the internal list of
     * {@code versionedUltiStudent}
     */
    @Override
    public ObservableList<ModuleCode> getFilteredModuleCodeList() {
        return filteredModuleCodeList;
    }

    @Override
    public void updateFilteredModuleCodeList(Predicate<ModuleCode> predicate) {
        requireNonNull(predicate);
        filteredModuleCodeList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<ModuleCode> selectedModuleCodeProperty() {
        return selectedModuleCode;
    }

    @Override
    public ModuleCode getSelectedModuleCode() {
        return selectedModuleCode.getValue();
    }

    @Override
    public void setSelectedModuleCode(ModuleCode moduleCode) {
        if (moduleCode != null && !filteredModuleSemesterList.contains(moduleCode)) {
            throw new ModuleCodeNotFoundException();
        }
        selectedModuleCode.setValue(moduleCode);
    }

    /**
     * Ensures {@code selectedModuleCode} is a valid cap entry in {@code filteredModuleCodeList}.
     */
    private void ensureSelectedModuleCodeIsValid(ListChangeListener.Change<? extends ModuleCode> change) {
        while (change.next()) {
            if (selectedModuleCode.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedModuleCodeReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedModuleCode.getValue());
            if (wasSelectedModuleCodeReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedModuleCode.getValue());
                selectedModuleCode.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedModuleCodeRemoved = change.getRemoved().stream()
                    .anyMatch(removedModuleCode -> selectedModuleCode.getValue().equals(removedModuleCode));
            if (wasSelectedModuleCodeRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedModuleCode.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1)
                        : null);
            }
        }
    }

    // ======================= Notes Manager ==================================
    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return versionedUltiStudent.hasNote(note);
    }

    @Override
    public void deleteNote(Note target) {
        versionedUltiStudent.removeNote(target);
    }

    @Override
    public void addNote(Note note) {
        versionedUltiStudent.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        versionedUltiStudent.setNote(target, editedNote);
    }

    //=========== Filtered Note List Accessors =================================

    /**
     * Returns an unmodifiable view of the list of {@code Note} backed by the
     * internal list of
     * {@code versionedUltiStudent}
     */

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNoteList;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNoteList.setPredicate(predicate);
    }

    //=========== Selected note ==============================================

    @Override
    public ReadOnlyProperty<Note> selectedNoteProperty() {
        return selectedNote;
    }

    @Override
    public Note getSelectedNote() {
        return selectedNote.getValue();
    }

    @Override
    public void setSelectedNote(Note note) {
        if (note != null && !filteredNoteList.contains(note)) {
            throw new NoteNotFoundException();
        }
        selectedNote.setValue(note);
    }

    /**
     * Ensures {@code selectedNote} is a valid note in {@code
     * filteredNoteList}.
     */
    private void ensureSelectedNoteIsValid(ListChangeListener.Change<? extends Note> change) {
        while (change.next()) {
            if (selectedNote.getValue() == null) {
                // null is always a valid selected note, so we do not need to
                // check that it is valid anymore.
                return;
            }

            boolean wasSelectedNoteReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedNote.getValue());
            if (wasSelectedNoteReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedNote.getValue
                        ());
                selectedNote.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedNoteRemoved = change.getRemoved().stream()
                    .anyMatch(removedNote -> selectedNote.getValue()
                            .isSameNote(removedNote));
            if (wasSelectedNoteRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedNote.setValue(change.getFrom() > 0 ? change.getList()
                        .get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedUltiStudent.equals(other.versionedUltiStudent)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredCapEntryList.equals(other.filteredCapEntryList)
                && filteredHomeworkList.equals(other.filteredHomeworkList);
    }
}
