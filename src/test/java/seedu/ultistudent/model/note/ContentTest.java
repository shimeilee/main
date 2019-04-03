package seedu.ultistudent.model.note;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class ContentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Content(null));
    }

    //@Test
    //public void constructor_invalidContent_throwsIllegalArgumentException() {
    //    String invalidContent = " ";
    //    Assert.assertThrows(IllegalArgumentException.class, () -> new
    //Content(invalidContent));
    //}

    @Test
    public void isValidNoteContent() {
        // null content
        Assert.assertThrows(NullPointerException.class, () -> Content
                .isValidNoteContent(null));

        // invalid content
        assertFalse(Content.isValidNoteContent(" ")); // spaces only

        // valid content
        assertTrue(Content.isValidNoteContent("lowercase")); // lowercase
        assertTrue(Content.isValidNoteContent("UPPERCASE")); // uppercase
        assertTrue(Content.isValidNoteContent("MixedCase")); // mixed case
        assertTrue(Content.isValidNoteContent("1234")); // numbers
        assertTrue(Content.isValidNoteContent("MixedCase123")); //alphanumerics
        assertTrue(Content.isValidNoteContent("Mixed Case 123")); // mixed case
    }
}
