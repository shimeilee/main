package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a homework entry in the address book.
 */
public class Homework {

    public static final String MESSAGE_CONSTRAINTS = "Homework should be inputted as MODULECODE; "
            + "HOMEWORKNAME; DEADLINE";

    private String moduleCode;
    private String homeworkName;
    private String deadline;


    //TOADD REGEX to only allow numbers, alphabets and spaces

    public Homework (String moduleCode, String homeworkName, String deadline) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;

        requireNonNull(homeworkName);
        this.homeworkName = homeworkName;

        requireNonNull(deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return moduleCode + "; " + homeworkName + "; " + deadline + "; ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
                && moduleCode.equals(((Homework) other).moduleCode)
                && homeworkName.equals(((Homework) other).homeworkName)
                && deadline.equals(((Homework) other).deadline));
    }

    @Override
    public int hashCode() {
        String value = moduleCode + " " + homeworkName + " " + deadline;
        return value.hashCode();
    }

}
