package seedu.address.model.cap;


/**
 * Represents a Homework's homework name in UltiStudent
 */
public class ModuleGrade {
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

    private String grade;
    private double points;

    public ModuleGrade(String grade) {
        this.grade = grade;
        switch (grade) {
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.grade);
        return builder.toString();
    }
}
