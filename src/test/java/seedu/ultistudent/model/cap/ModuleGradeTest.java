package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class ModuleGradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleGrade(null));
    }

    @Test
    public void constructor_invalidModuleGrade_throwsIllegalArgumentException() {
        String invalidModuleGrade = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleGrade(invalidModuleGrade));
    }

    @Test
    public void isValidModuleGrade() {
        // null module grade
        Assert.assertThrows(NullPointerException.class, () -> ModuleGrade.isValidModuleGrade(null));

        // invalid module grades
        assertFalse(ModuleGrade.isValidModuleGrade("")); // empty string
        assertFalse(ModuleGrade.isValidModuleGrade(" ")); // spaces only
        assertFalse(ModuleGrade.isValidModuleGrade("Q")); // invalid character
        assertFalse(ModuleGrade.isValidModuleGrade("AB")); // more than 1 character
        assertFalse(ModuleGrade.isValidModuleGrade("A+-")); // more than 1 sign
        assertFalse(ModuleGrade.isValidModuleGrade("123")); // non-alphabetical
        assertFalse(ModuleGrade.isValidModuleGrade("A1")); // alphabets and digits
        assertFalse(ModuleGrade.isValidModuleGrade("A +")); // space between sign and grade

        // valid module grades
        assertTrue(ModuleGrade.isValidModuleGrade("A+")); // exactly 1 alphabet and 1 sign
        assertTrue(ModuleGrade.isValidModuleGrade("B")); // exactly 1 alphabet
        assertTrue(ModuleGrade.isValidModuleGrade("B-"));
    }
}
