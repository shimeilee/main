package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class ModuleCreditsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleCredits(null));
    }

    @Test
    public void constructor_invalidModuleCredits_throwsIllegalArgumentException() {
        String invalidModuleCredits = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleCredits(invalidModuleCredits));
    }

    @Test
    public void isValidModuleCreditsPhone() {
        // null module credits
        Assert.assertThrows(NullPointerException.class, () -> ModuleCredits.isValidModuleCredits(null));

        // invalid module credits
        assertFalse(ModuleCredits.isValidModuleCredits("")); // empty string
        assertFalse(ModuleCredits.isValidModuleCredits(" ")); // spaces only
        assertFalse(ModuleCredits.isValidModuleCredits("921")); // less than 3 numbers
        assertFalse(ModuleCredits.isValidModuleCredits("two")); // non-numeric
        assertFalse(ModuleCredits.isValidModuleCredits("1one2")); // alphabets within digits
        assertFalse(ModuleCredits.isValidModuleCredits("3 4")); // spaces within digits

        // valid module credits
        assertTrue(ModuleCredits.isValidModuleCredits("4")); // exactly 1 numbers
        assertTrue(ModuleCredits.isValidModuleCredits("12")); //exactly 2 numbers
        assertTrue(ModuleCredits.isValidModuleCredits("0")); // 0
    }
}
