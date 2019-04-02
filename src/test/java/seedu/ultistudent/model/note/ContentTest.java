package seedu.ultistudent.model.note;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class ContentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Content(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidContent = " ";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Content((invalidContent)));
    }

    //@Test
    //public void isValidContent() {
        // null UltiStudent
        //Assert.assertThrows(NullPointerException.class, () -> Content
        //        .isValidNoteContent(null));

        // invalid addresses
        //assertFalse(Content.isValidNoteContent("")); // empty string
        //assertFalse(Content.isValidNoteContent(" ")); // spaces only

        // valid addresses
        //assertTrue(Content.isValidNoteContent("i am awesome")); // lowercase
        // characters and spaces
        //assertTrue(Content.isValidNoteContent("-")); // one character
        // assertTrue(Content.isValidNoteContent("Print('Give me an A please');"
        //     + "San Francisco CA 2349879; " + "USA")); //long content
    //}
}
