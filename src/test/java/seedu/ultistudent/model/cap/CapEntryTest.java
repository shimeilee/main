package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CREDITS_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1002;

import static seedu.ultistudent.testutil.TypicalCapEntry.CS1002;
import static seedu.ultistudent.testutil.TypicalCapEntry.CS2101;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.testutil.CapEntryBuilder;

public class CapEntryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isSameCapEntry() {
        // same object -> returns true
        assertTrue(CS2101.isSameCapEntry(CS2101));

        // null -> returns false
        assertFalse(CS2101.isSameCapEntry(null));

        // different moduleCode -> returns false
        CapEntry editedCS2101 = new CapEntryBuilder(CS2101).withModuleCode(VALID_MODULE_CODE_CS1002).build();
        assertFalse(CS2101.isSameCapEntry(editedCS2101));

        // same moduleCode, different attributes -> returns true
        editedCS2101 = new CapEntryBuilder(CS2101).withModuleCredits(VALID_MODULE_CREDITS_CS1002)
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).withModuleSemester(VALID_MODULE_SEMESTER_CS1002).build();
        assertTrue(CS2101.isSameCapEntry(editedCS2101));
    }

    @Test
    public void equals() {
        // same values -> returns true
        CapEntry cs2101Copy = new CapEntryBuilder(CS2101).build();
        assertTrue(CS2101.equals(cs2101Copy));

        // same object -> returns true
        assertTrue(CS2101.equals(CS2101));

        // null -> returns false
        assertFalse(CS2101.equals(null));

        // different type -> returns false
        assertFalse(CS2101.equals(5));

        // different capEntry -> returns false
        assertFalse(CS2101.equals(CS1002));

        // different moduleCode -> returns false
        CapEntry editedCs2101 = new CapEntryBuilder(CS2101).withModuleCode(VALID_MODULE_CODE_CS1002).build();
        assertFalse(CS2101.equals(editedCs2101));

        // different moduleSemester -> returns false
        editedCs2101 = new CapEntryBuilder(CS2101).withModuleSemester(VALID_MODULE_SEMESTER_CS1002).build();
        assertFalse(CS2101.equals(editedCs2101));

        // different moduleCredits -> returns false
        editedCs2101 = new CapEntryBuilder(CS2101).withModuleCredits(VALID_MODULE_CREDITS_CS1002).build();
        assertFalse(CS2101.equals(editedCs2101));

        // different moduleGrade -> returns false
        editedCs2101 = new CapEntryBuilder(CS2101).withModuleGrade(VALID_MODULE_GRADE_CS1002).build();
        assertFalse(CS2101.equals(editedCs2101));

    }

}
