package seedu.address.model.cap;


/**
 * Represents a Homework's homework name in UltiStudent
 */
public class ModuleGrade {

    public static final String MESSAGE_CONSTRAINTS = "Module Grades should begin with One letter "
            + "may end with an optional + or - sign at the back";

    public static final String VALIDATION_REGEX = "\\w{1}[+-]{0,1}";

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
    private double points;

    /**
     * Constructs a {@code ModuleCode}.
     * @param moduleGrade a valid moduleGrade
     */
    public ModuleGrade(String moduleGrade) {
        this.value = moduleGrade;
        switch (moduleGrade) {
        case A_PLUS_GRADE: case A_GRADE:
            this.points = 5.0;
            break;
        case A_MINUS_GRADE:
            this.points = 4.5;
            break;
        case B_PLUS_GRADE:
            this.points = 4.0;
            break;
        case B_GRADE:
            this.points = 3.5;
            break;
        case B_MINUS_GRADE:
            this.points = 3.0;
            break;
        case C_PLUS_GRADE:
            this.points = 2.5;
            break;
        case C_GRADE:
            this.points = 2.0;
            break;
        case D_PLUS_GRADE:
            this.points = 1.5;
            break;
        case D_GRADE:
            this.points = 1.0;
            break;
        case F_GRADE:
            this.points = 0.0;
            break;
        default:
            break;
        }
    }

    public double getPoints() {
        return this.points;
    }

    public String getGrade() {
        return this.value;
    }

    //TODO: Regex check for moduleGrade
    /**
     * Returns true if it is a valid ModuleGrade
     */
    public static boolean isValidModuleGrade(String test) {
        return test.matches(VALIDATION_REGEX);
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
