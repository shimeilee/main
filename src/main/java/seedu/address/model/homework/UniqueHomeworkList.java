package seedu.address.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.homework.exceptions.DuplicateHomeworkException;
import seedu.address.model.homework.exceptions.HomeworkNotFoundException;

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
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Homework toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateHomeworkException();
        }
        internalList.add(toAdd);
    }

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
     *Returns true if {@code homeworks}
     */
    private boolean homeworkAreUnique(List<Homework> homework) {
        for (int i = 0; i < homework.size() - 1; i++) {
            for (int j = i + 1; j < homework.size(); j++) {
                if (homework.get(i).equals(homework.get(j))) {
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
