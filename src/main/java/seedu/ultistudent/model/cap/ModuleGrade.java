package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;

import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

/**
 * Represents a CapEntry's module grade in UltiStudent
 */
public class ModuleGrade {

    public static final String MESSAGE_CONSTRAINTS = "Module Grades should begin with one letter  and "
            + "may end with an optional + or - sign at the back. \n"
            + "Valid grades are: A+, A, A-, B+, B, B-, C+, C, D+, D, F";

    public static final String VALIDATION_REGEX = "([AB]{1}[+-]{0,1})|([CD]{1}[+]{0,1})|[F]";

    public static final String A_PLUS_GRADE = "A+";
    public static final String A_GRADE = "A";
    public static final String A_MINUS_GRADE = "A-";
    public static final String B_PLUS_GRADE = "B+";
    public static final String B_GRADE = "B";
    public static final String B_MINUS_GRADE = "B-";
    public static final String C_PLUS_GRADE = "C+";
    public static final String C_GRADE = "C";
    public static final String D_PLUS_GRADE = "D+";
    public static final String D_GRADE = "D";
    public static final String F_GRADE = "F";

    public final String value;
    private double score;

    /**
     * Constructs a {@code ModuleGrade}.
     * @param moduleGrade a valid moduleGrade
     */
    public ModuleGrade(String moduleGrade) {
        String capitalisedModuleGrade = moduleGrade.toUpperCase();
        requireNonNull(moduleGrade);
        checkArgument(isValidModuleGrade(capitalisedModuleGrade), MESSAGE_CONSTRAINTS);
        this.value = capitalisedModuleGrade;

        switch (capitalisedModuleGrade) {
        case A_PLUS_GRADE: case A_GRADE:
            this.score = 5.0;
            break;
        case A_MINUS_GRADE:
            this.score = 4.5;
            break;
        case B_PLUS_GRADE:
            this.score = 4.0;
            break;
        case B_GRADE:
            this.score = 3.5;
            break;
        case B_MINUS_GRADE:
            this.score = 3.0;
            break;
        case C_PLUS_GRADE:
            this.score = 2.5;
            break;
        case C_GRADE:
            this.score = 2.0;
            break;
        case D_PLUS_GRADE:
            this.score = 1.5;
            break;
        case D_GRADE:
            this.score = 1.0;
            break;
        case F_GRADE:
            this.score = 0.0;
            break;
        default:
            break;
        }
    }

    public double getScore() {
        return this.score;
    }

    public String getGrade() {
        return this.value;
    }

    /**
     * Returns true if it is a valid ModuleGrade
     */
    public static boolean isValidModuleGrade(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleGrade // instanceof handles nulls
                && value.equals(((ModuleGrade) other).value)); // state check
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.value);
        return builder.toString();
    }
}
