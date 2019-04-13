package seedu.ultistudent.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ultistudent.model.homework.exceptions.DuplicateModuleCodeException;
import seedu.ultistudent.model.homework.exceptions.ModuleCodeNotFoundException;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * A list of module codes that enforces uniqueness between its elements and does not allow nulls.
 * A module code is considered unique by comparing using {@code ModuleCode#equals(ModuleCode)}. As such,
 * adding and updating of module codes uses ModuleCode#equals(ModuleCode) for equality so as to ensure that
 * the module code being added or updated is unique in terms of identity in the UniqueModuleCodeList. Similarly,
 * the removal of a cap entry uses ModuleCode#equals(Object) so as to ensure that the cap entry with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */

public class UniqueModuleCodeList implements Iterable<ModuleCode> {

    private final ObservableList<ModuleCode> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleCode> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent ModuleCode as the given argument.
     */
    public boolean contains(ModuleCode toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a ModuleCode to the list.
     * The ModuleCode must not already exist in the list.
     */
    public void add(ModuleCode toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleCodeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the modeule code {@code target} in the list with {@code editedModuleCode}.
     * {@code target} must exist in the list.
     * The module code identity of {@code editedModuleCode} must not be the same as another
     * existing module code in the list.
     */
    public void setModuleCode(ModuleCode target, ModuleCode editedModuleCode) {
        requireAllNonNull(target, editedModuleCode);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleCodeNotFoundException();
        }

        if (!target.equals(editedModuleCode) && contains(editedModuleCode)) {
            throw new DuplicateModuleCodeException();
        }

        internalList.set(index, editedModuleCode);
    }

    /**
     * Removes the equivalent module code from the list.
     * The module code must exist in the list.
     */
    public void remove(ModuleCode toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleCodeNotFoundException();
        }
    }

    public void setModuleCodeList(UniqueModuleCodeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code moduleCode}.
     * {@code moduleCode} must not contain duplicate module codes.
     */
    public void setModuleCodeList(List<ModuleCode> moduleCodeList) {
        requireAllNonNull(moduleCodeList);
        if (!moduleCodeListIsUnique(moduleCodeList)) {
            throw new DuplicateModuleCodeException();
        }

        internalList.setAll(moduleCodeList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleCode> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleCode> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleCodeList // instanceof handles nulls
                && internalList.equals(((UniqueModuleCodeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code moduleCodeList} contains only unique module codes.
     */
    private boolean moduleCodeListIsUnique(List<ModuleCode> moduleCodeList) {
        for (int i = 0; i < moduleCodeList.size() - 1; i++) {
            for (int j = i + 1; j < moduleCodeList.size(); j++) {
                if (moduleCodeList.get(i).equals(moduleCodeList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
