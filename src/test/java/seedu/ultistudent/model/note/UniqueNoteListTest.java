package seedu.ultistudent.model.note;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_CONTENT_LOWER;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_FIRST;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_SECOND;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.model.note.exceptions.DuplicateNoteException;
import seedu.ultistudent.model.note.exceptions.NoteNotFoundException;
import seedu.ultistudent.testutil.NoteBuilder;

public class UniqueNoteListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueNoteList uniqueNoteList = new UniqueNoteList();

    @Test
    public void contains_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.contains(null);
    }

    @Test
    public void contains_noteNotInList_returnsFalse() {
        assertFalse(uniqueNoteList.contains(NOTE_FIRST));
    }

    @Test
    public void contains_noteInList_returnsTrue() {
        uniqueNoteList.add(NOTE_FIRST);
        assertTrue(uniqueNoteList.contains(NOTE_FIRST));
    }

    //@Test
    //public void contains_noteWithSameIdentityFieldsInList_returnsTrue() {
    //uniqueNoteList.add(NOTE_FIRST);
    //Note editedNoteFirst = new NoteBuilder(NOTE_FIRST).withContent
    //(VALID_CONTENT_LOWER)
    // .build();
    //assertTrue(uniqueNoteList.contains(editedNoteFirst));
    //}

    @Test
    public void add_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.add(null);
    }

    @Test
    public void add_duplicateNote_throwsDuplicateNoteException() {
        uniqueNoteList.add(NOTE_FIRST);
        thrown.expect(DuplicateNoteException.class);
        uniqueNoteList.add(NOTE_FIRST);
    }

    @Test
    public void setNote_nullTargetNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.setNote(null, NOTE_FIRST);
    }

    @Test
    public void setNote_nullEditedNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.setNote(NOTE_FIRST, null);
    }

    @Test
    public void setNote_targetNoteNotInList_throwsNoteNotFoundException() {
        thrown.expect(NoteNotFoundException.class);
        uniqueNoteList.setNote(NOTE_FIRST, NOTE_FIRST);
    }

    @Test
    public void setNote_editedNoteIsSameNote_success() {
        uniqueNoteList.add(NOTE_FIRST);
        uniqueNoteList.setNote(NOTE_FIRST, NOTE_FIRST);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(NOTE_FIRST);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void setNote_editedNoteHasSameIdentity_success() {
        uniqueNoteList.add(NOTE_FIRST);
        Note editedNoteFirst = new NoteBuilder(NOTE_FIRST).withContent(VALID_CONTENT_LOWER)
                .build();
        uniqueNoteList.setNote(NOTE_FIRST, editedNoteFirst);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(editedNoteFirst);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void setNote_editedNoteHasDifferentIdentity_success() {
        uniqueNoteList.add(NOTE_FIRST);
        uniqueNoteList.setNote(NOTE_FIRST, NOTE_SECOND);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(NOTE_SECOND);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void
        setNote_editedNoteHasNonUniqueIdentity_throwsDuplicateNoteException() {
        uniqueNoteList.add(NOTE_FIRST);
        uniqueNoteList.add(NOTE_SECOND);
        thrown.expect(DuplicateNoteException.class);
        uniqueNoteList.setNote(NOTE_FIRST, NOTE_SECOND);
    }

    @Test
    public void remove_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.remove(null);
    }

    @Test
    public void remove_noteDoesNotExist_throwsNoteNotFoundException() {
        thrown.expect(NoteNotFoundException.class);
        uniqueNoteList.remove(NOTE_FIRST);
    }

    @Test
    public void remove_existingNote_removesNote() {
        uniqueNoteList.add(NOTE_FIRST);
        uniqueNoteList.remove(NOTE_FIRST);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void setNotes_nullUniqueNotesList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.setNotes((UniqueNoteList) null);
    }

    @Test
    public void
        setNotes_uniqueNoteList_replacesOwnListWithProvidedUniqueNoteList() {
        uniqueNoteList.add(NOTE_FIRST);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(NOTE_SECOND);
        uniqueNoteList.setNotes(expectedUniqueNoteList);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void setNotes_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueNoteList.setNotes((List<Note>) null);
    }

    @Test
    public void setNotes_list_replacesOwnListWithProvidedList() {
        uniqueNoteList.add(NOTE_FIRST);
        List<Note> noteList = Collections.singletonList(NOTE_SECOND);
        uniqueNoteList.setNotes(noteList);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(NOTE_SECOND);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    public void
        setNotes_listWithDuplicateNotess_throwsDuplicateNoteException() {
        List<Note> listWithDuplicateNotes = Arrays.asList(NOTE_FIRST, NOTE_FIRST);
        thrown.expect(DuplicateNoteException.class);
        uniqueNoteList.setNotes(listWithDuplicateNotes);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueNoteList.asUnmodifiableObservableList().remove(0);
    }
}
