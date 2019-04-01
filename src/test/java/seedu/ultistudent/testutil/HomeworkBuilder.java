package seedu.ultistudent.testutil;

import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * A utility class to help with building Person objects.
 */
public class HomeworkBuilder {

    public static final String DEFAULT_MODULECODE = "CS2103T";
    public static final String DEFAULT_HOMEWORK = "Tutorial 5";
    public static final String DEFAULT_DEADLINE = "15/03/2019";

    private ModuleCode moduleCode;
    private HomeworkName homeworkName;
    private Date deadline;

    public HomeworkBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULECODE);
        homeworkName = new HomeworkName(DEFAULT_HOMEWORK);
        deadline = new Date(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public HomeworkBuilder(Homework homeworkToCopy) {
        moduleCode = homeworkToCopy.getModuleCode();
        homeworkName = homeworkToCopy.getHomeworkName();
        deadline = homeworkToCopy.getDeadline();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Homework} that we are building.
     */
    public HomeworkBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code HomeworkName} of the {@code Homework} that we are building.
     */
    public HomeworkBuilder withHomeworkName(String homeworkName) {
        this.homeworkName = new HomeworkName(homeworkName);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Homework} that we are building.
     */
    public HomeworkBuilder withDeadline(String deadline) {
        this.deadline = new Date(deadline);
        return this;
    }

    public Homework build() {
        return new Homework(moduleCode, homeworkName, deadline);
    }

}
