package seedu.ultistudent.model.note;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_CONTENT_LOWER;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_NOTE_NAME_LOWER;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_FIRST;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_SECOND;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.testutil.NoteBuilder;


public class NoteTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isSameNote() {
        // same object -> returns true
        assertTrue(NOTE_FIRST.isSameNote(NOTE_FIRST));

        // null -> returns false
        assertFalse(NOTE_FIRST.isSameNote(null));

        // different note name, module code, and content -> returns false
        Note editedNoteFirst = new NoteBuilder(NOTE_FIRST).withNoteName
                (VALID_NOTE_NAME_LOWER)
                .withModuleCode(VALID_MODULE_CODE_CS1001)
                .withContent(VALID_CONTENT_LOWER).build();
        assertFalse(NOTE_FIRST.isSameNote(editedNoteFirst));

        // different note name -> returns false
        editedNoteFirst = new NoteBuilder(NOTE_FIRST).withNoteName(VALID_NOTE_NAME_LOWER)
                .build();
        assertFalse(NOTE_FIRST.isSameNote(editedNoteFirst));

        // different module code -> returns false
        editedNoteFirst = new NoteBuilder(NOTE_FIRST).withModuleCode
                (VALID_MODULE_CODE_CS1001)
                .build();
        assertFalse(NOTE_FIRST.isSameNote(editedNoteFirst));

        // same note name and module code, different content -> returns false
        editedNoteFirst = new NoteBuilder(NOTE_FIRST).withContent
                (VALID_CONTENT_LOWER).build();
        assertFalse(NOTE_FIRST.isSameNote(editedNoteFirst));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Note noteFirstCopy = new NoteBuilder(NOTE_FIRST).build();
        assertTrue(NOTE_FIRST.equals(noteFirstCopy));

        // same object -> returns true
        assertTrue(NOTE_FIRST.equals(NOTE_FIRST));

        // null -> returns false
        assertFalse(NOTE_FIRST.equals(null));

        // different type -> returns false
        assertFalse(NOTE_FIRST.equals(5));

        // different note -> returns false
        assertFalse(NOTE_FIRST.equals(NOTE_SECOND));

        // different note name -> returns false
        Note noteFirstCopy1 = new NoteBuilder(NOTE_FIRST).withNoteName
                (VALID_NOTE_NAME_LOWER)
                .build();
        assertFalse(NOTE_FIRST.equals(noteFirstCopy1));

        // different module code -> returns false
        noteFirstCopy1 = new NoteBuilder(NOTE_FIRST).withModuleCode
                (VALID_MODULE_CODE_CS1001)
                .build();
        assertFalse(NOTE_FIRST.equals(noteFirstCopy1));
    }
}
