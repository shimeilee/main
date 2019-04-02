package seedu.ultistudent.model.homework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class HomeworkNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new HomeworkName(null));
    }

    @Test
    public void constructor_invalidHomeworkName_throwsIllegalArgumentException() {
        String invalidHomework = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new HomeworkName(invalidHomework));
    }

    @Test
    public void isValidHomeworkName() {
        // null module code
        Assert.assertThrows(NullPointerException.class, () -> HomeworkName.isValidHomeworkName(null));

        // invalid module codes
        assertFalse(HomeworkName.isValidHomeworkName("")); // empty string
        assertFalse(HomeworkName.isValidHomeworkName("!@$^&(()")); // symbols are not allowed
        assertFalse(HomeworkName.isValidHomeworkName("Milestone 1.1")); // dots are not allowed



        // valid module codes
        assertTrue(HomeworkName.isValidHomeworkName("Tutorial 1"));
        assertTrue(HomeworkName.isValidHomeworkName("Lecture 9")); // module code with a character at the end
        assertTrue(HomeworkName.isValidHomeworkName("User Guide Submission"));


    }
}
