package seedu.ultistudent.model.note;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class NoteNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new NoteName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidNoteName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new
                NoteName((invalidNoteName)));
    }

    @Test
    public void isValidNoteName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> NoteName
                .isValidNoteName(null));

        // invalid name
        assertFalse(NoteName.isValidNoteName("")); // empty string
        assertFalse(NoteName.isValidNoteName(" ")); // spaces only
        assertFalse(NoteName.isValidNoteName("^")); // only non-alphanumeric
        // characters
        assertFalse(NoteName.isValidNoteName("peter*")); // contains
        // non-alphanumeric characters

        // valid name
        assertTrue(NoteName.isValidNoteName("week one")); // alphabets only
        assertTrue(NoteName.isValidNoteName("12345")); // numbers only
        assertTrue(NoteName.isValidNoteName("week 2 notes")); //alphanumeric characters
        assertTrue(NoteName.isValidNoteName("Week 3")); // with capital letters
        assertTrue(NoteName.isValidNoteName("Week 2 Notes Revised with Notes"
                + " from Prof")); // long note names
    }
}
