package seedu.ultistudent.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * A utility class containing a list of {@code ModuleSemester} objects to be used in tests.
 */
public class TypicalModuleSemester {
    public static final ModuleSemester SEMESTER_FIRST = new ModuleSemesterBuilder().withModuleSemester("Y1S1")
            .build();
    public static final ModuleSemester SEMESTER_SECOND = new ModuleSemesterBuilder().withModuleSemester("Y1S2")
            .build();

    public static final ModuleSemester SEMESTER_THIRD = new ModuleSemesterBuilder().withModuleSemester("Y2S1")
            .build();
    public static final ModuleSemester SEMESTER_FOURTH = new ModuleSemesterBuilder().withModuleSemester("Y2S2")
            .build();
    public static final ModuleSemester SEMESTER_FIFTH = new ModuleSemesterBuilder().withModuleSemester("Y3S1")
            .build();
    public static final ModuleSemester SEMESTER_SIXTH = new ModuleSemesterBuilder().withModuleSemester("Y3S2")
            .build();
    public static final ModuleSemester SEMESTER_SEVENTH = new ModuleSemesterBuilder().withModuleSemester("Y4S1")
            .build();

    // Manually added
    public static final ModuleSemester SEMESTER_EIGHTH = new ModuleSemesterBuilder().withModuleSemester("Y4S2")
            .build();
    public static final ModuleSemester SEMESTER_NINETH = new ModuleSemesterBuilder().withModuleSemester("Y5S1")
            .build();

    // Manually added - Module Semester's details found in {@code CommandTestUtil}
    public static final ModuleSemester SEMESTER_TENTH = new ModuleSemesterBuilder().withModuleSemester("Y5S2")
            .build();
    public static final ModuleSemester SEMESTER_ELEVENTH = new ModuleSemesterBuilder().withModuleSemester("Y4S2")
            .build();


    private TypicalModuleSemester() {} // prevents instantiation

    /**
     * Returns an {@code UltiStudent} with all the typical moduleSemester.
     */
    public static UltiStudent getTypicalAddressBook() {
        UltiStudent ab = new UltiStudent();
        for (ModuleSemester moduleSemester : getTypicalModuleSemester()) {
            ab.addModuleSemester(moduleSemester);
        }
        return ab;
    }

    public static List<ModuleSemester> getTypicalModuleSemester() {
        return new ArrayList<>(Arrays.asList(SEMESTER_FIRST, SEMESTER_SECOND, SEMESTER_THIRD,
                SEMESTER_FOURTH, SEMESTER_FIFTH, SEMESTER_SIXTH, SEMESTER_SEVENTH));
    }
}
