package seedu.ultistudent.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.ultistudent.model.homework.exceptions.DuplicateHomeworkException;
import seedu.ultistudent.model.homework.exceptions.HomeworkNotFoundException;

/**
 * A list of homework that enforces uniqueness between its elements and does not allow nulls.
 * A homework is considered unique by comparing using Homework#equals(other). As such, adding and updating of
 * homework uses Homework#equals(other) for equality so as to ensure that the homework being added or updated is
 * unique in terms of identity in the UniqueHomeworkList. The removal of a homework uses Homework#equals(other) so
 * as to ensure that the homework with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueHomeworkList implements Iterable<Homework> {

    private final ObservableList<Homework> internalList = FXCollections.observableArrayList();
    private final ObservableList<Homework> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent homework as the given argument.
     */
    public boolean contains(Homework toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a homework to the list.
     * The homework must not already exist in the list.
     */
    public void add(Homework toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateHomeworkException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent homework from the list.
     * The homework must exist in the list.
     */
    public void remove(Homework toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new HomeworkNotFoundException();
        }
    }

    public void setHomework(List<Homework> homework) {
        requireAllNonNull(homework);
        if (!homeworkAreUnique(homework)) {
            throw new DuplicateHomeworkException();
        }

        internalList.setAll(homework);
    }

    public void setHomework(UniqueHomeworkList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setHomework(Homework target, Homework editedHomework) {
        requireAllNonNull(target, editedHomework);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new HomeworkNotFoundException();
        }

        if (!target.equals(editedHomework) && contains(editedHomework)) {
            throw new DuplicateHomeworkException();
        }

        internalList.set(index, editedHomework);
    }

    /**
     *Returns true if {@code homeworkList} contains only unique module codes.
     */
    private boolean homeworkAreUnique(List<Homework> homeworkList) {
        for (int i = 0; i < homeworkList.size() - 1; i++) {
            for (int j = i + 1; j < homeworkList.size(); j++) {
                if (homeworkList.get(i).equals(homeworkList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public Iterator<Homework> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Homework> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

}
