package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;

import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a CapEntry's module grade in UltiStudent
 */
public class ModuleGrade {

    public static final String MESSAGE_CONSTRAINTS = "Module Grades should begin with one letter  and "
            + "may end with an optional + or - sign at the back. \n"
            + "Valid grades are: A+, A, A-, B+, B, B-, C+, C, D+, D, F";

    public static final String VALIDATION_REGEX = "([AB]{1}[+-]{0,1})|([CD]{1}[+]{0,1})|[F]";

    private static final Map<String, Float> scoreSystem = new HashMap<>() {
        {
            put("A+", 5.0F);
            put("A", 5.0F);
            put("A-", 4.5F);
            put("B+", 4.0F);
            put("B", 3.5F);
            put("B-", 3.0F);
            put("C+", 2.5F);
            put("C", 2.0F);
            put("D+", 1.5F);
            put("D", 1.0F);
            put("F", 0.0F);
        }
    };

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
        this.score = scoreSystem.get(capitalisedModuleGrade);
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
