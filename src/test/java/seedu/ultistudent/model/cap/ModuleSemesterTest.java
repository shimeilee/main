package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class ModuleSemesterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleSemester(null));
    }

    @Test
    public void constructor_invalidModuleSemester_throwsIllegalArgumentException() {
        String invalidModuleSemester = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleGrade(invalidModuleSemester));
    }

    @Test
    public void isValidModuleSemester() {
        // null module semester
        Assert.assertThrows(NullPointerException.class, () -> ModuleSemester.isValidModuleSemester(null));

        // invalid phone numbers
        assertFalse(ModuleSemester.isValidModuleSemester("")); // empty string
        assertFalse(ModuleSemester.isValidModuleSemester(" ")); // spaces only
        assertFalse(ModuleSemester.isValidModuleSemester("Y6S1")); // Year is more than the range
        assertFalse(ModuleSemester.isValidModuleSemester("Y-3S1")); // Year is less than the range
        assertFalse(ModuleSemester.isValidModuleSemester("Y1S7")); // Semester is more than the range
        assertFalse(ModuleSemester.isValidModuleSemester("Y1S0")); // Semester is less than the range
        assertFalse(ModuleSemester.isValidModuleSemester("Y7S3")); // Both Year and Semester are over the range
        assertFalse(ModuleSemester.isValidModuleSemester("Y0S-4")); // Both Year and Semester are under the range
        assertFalse(ModuleSemester.isValidModuleSemester("U3S3")); // Incorrect label of Y
        assertFalse(ModuleSemester.isValidModuleSemester("Y3X3")); // Incorrect label of S
        assertFalse(ModuleSemester.isValidModuleSemester("Y3 S2")); // Space between Year and Semester label


        // valid phone numbers
        assertTrue(ModuleSemester.isValidModuleSemester("Y1S2")); // exactly 1 alphabet and 1 sign
        assertTrue(ModuleSemester.isValidModuleSemester("Y5S2")); // exactly maximum range of Y and S
        assertTrue(ModuleSemester.isValidModuleSemester("Y1S1")); // exactly minimum range of Y and S
    }
}
