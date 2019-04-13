package seedu.ultistudent.model.homework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.ultistudent.testutil.Assert;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new HomeworkName(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null module code
        Assert.assertThrows(NullPointerException.class, () -> Date.isValidDate(null, ));

        // invalid module codes
        assertFalse(Date.isValidDate("", )); // empty string
        assertFalse(Date.isValidDate(" ", )); // spaces only
        assertFalse(Date.isValidDate("32/01/2019", )); // invalid date because there is no Jan 32
        assertFalse(Date.isValidDate("30/02/2019", )); // invalid date because there is no Feb 30
        assertFalse(Date.isValidDate("29/02/2019", )); // invalid because 2019 is not a leap year
        assertFalse(Date.isValidDate("15/13/2019", )); // invalid because there are 12 months in a year
        assertFalse(Date.isValidDate("15/13/01", )); // invalid format for year


        // valid module codes
        assertTrue(Date.isValidDate("01/01/2020", ));
        assertTrue(Date.isValidDate("01/1/2020", )); // single digit for month
        assertTrue(Date.isValidDate("1/01/2020", )); // single digit for day

    }
}
