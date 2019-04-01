package seedu.ultistudent.model.homework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.testutil.Assert;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() {
        // null module code
        Assert.assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid module codes
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("CSCS1010 ")); // module code with 4 alphabets at the start
        assertFalse(ModuleCode.isValidModuleCode("CS101")); // module code with only 3 numbers
        assertFalse(ModuleCode.isValidModuleCode("CS 1010 ")); // module code with spaces in between


        // valid module codes
        assertTrue(ModuleCode.isValidModuleCode("CS2101"));
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // module code with a character at the end
        assertTrue(ModuleCode.isValidModuleCode("LSM1301")); // module code with 3 alphabets at the start
        // module code with 3 alphabets at the start and 1 alphabet at the end
        assertTrue(ModuleCode.isValidModuleCode("LSM1301T"));

    }
}
