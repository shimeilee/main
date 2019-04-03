package seedu.ultistudent.model.note;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.*;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_FIRST;
import static seedu.ultistudent.testutil.TypicalNote.NOTE_SECOND;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.testutil.NoteBuilder;


public class NoteTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //@Test
    //public void
    // asObservableList_modifyList_throwsUnsupportedOperationException() {
    // Note note = new NoteBuilder().build();
    // thrown.expect(UnsupportedOperationException.class);
    //note.getTags().remove(0);
    //}

    @Test
    public void isSameNote() {
        // same object -> returns true
        assertTrue(NOTE_FIRST.isSameNote(NOTE_FIRST));

        // null -> returns false
        assertFalse(NOTE_FIRST.isSameNote(null));

        // different phone and email -> returns false
        Note editedNoteFirst = new NoteBuilder(NOTE_FIRST).withNoteName
                (VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ultistudent -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
