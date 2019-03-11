package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Homework's homework name in UltiStudent
 */
public class HomeworkName {

    public static final String MESSAGE_CONSTRAINTS = "Homework names can contain letters and numbers.";
    //public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    public HomeworkName(String homeworkName) {
        requireNonNull(homeworkName);
        value = homeworkName;
    }

    //TODO: Regex check for Homework Name
    /**
     * Returns true if it is a valid ModuleCode
     */
    public static boolean isValidHomeworkName(String test) {
        return true;
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
