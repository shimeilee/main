package seedu.ultistudent.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

/**
 * Represents a Homework's homework name in UltiStudent.
 */
public class HomeworkName {

    public static final String MESSAGE_CONSTRAINTS = "Homework names can only contain letters,"
            + " whitespaces and numbers.";
    public static final String VALIDATION_REGEX = "[a-zA-Z\\s\\d]{1,}";
    public final String value;

    public HomeworkName(String homeworkName) {
        requireNonNull(homeworkName);
        checkArgument(isValidHomeworkName(homeworkName), MESSAGE_CONSTRAINTS);
        value = homeworkName;
    }

    /**
     * Returns true if it is a valid HomeworkName.
     */
    public static boolean isValidHomeworkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeworkName // instanceof handles nulls
                && value.equals(((HomeworkName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
