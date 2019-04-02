//package seedu.ultistudent.model.cap;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static seedu.ultistudent.testutil.TypicalPersons.ALICE;
//import static seedu.ultistudent.testutil.TypicalPersons.BOB;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import seedu.ultistudent.model.cap.exceptions.CapEntryNotFoundException;
//import seedu.ultistudent.model.cap.exceptions.DuplicateCapEntryException;
//import seedu.ultistudent.testutil.CapEntryBuilder;
//
//
//public class UniqueCapEntryListTest {
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    private final UniqueCapEntryList uniqueCapEntryList = new UniqueCapEntryList();
//
//    @Test
//    public void contains_nullCapEntry_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.contains(null);
//    }
//
//    @Test
//    public void contains_personNotInList_returnsFalse() {
//        assertFalse(uniqueCapEntryList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_personInList_returnsTrue() {
//        uniqueCapEntryList.add(ALICE);
//        assertTrue(uniqueCapEntryList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
//        uniqueCapEntryList.add(ALICE);
//        CapEntry editedAlice = new CapEntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        assertTrue(uniqueCapEntryList.contains(editedAlice));
//    }
//
//    @Test
//    public void add_nullCapEntry_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.add(null);
//    }
//
//    @Test
//    public void add_duplicatePerson_throwsDuplicatePersonException() {
//        uniqueCapEntryList.add(ALICE);
//        thrown.expect(DuplicateCapEntryException.class);
//        uniqueCapEntryList.add(ALICE);
//    }
//
//    @Test
//    public void setCapEntry_nullTargetPerson_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.setCapEntry(null, ALICE);
//    }
//
//    @Test
//    public void setPerson_nullEditedPerson_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.setCapEntry(ALICE, null);
//    }
//
//    @Test
//    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
//        thrown.expect(CapEntryNotFoundException.class);
//        uniqueCapEntryList.setPerson(ALICE, ALICE);
//    }
//
//    @Test
//    public void setPerson_editedPersonIsSamePerson_success() {
//        uniqueCapEntryList.add(ALICE);
//        uniqueCapEntryList.setCapEntry(ALICE, ALICE);
//        UniqueCapEntryList expectedUniqueCaoEntryList = new UniqueCapEntryList();
//        expectedUniqueCaoEntryList.add(ALICE);
//        assertEquals(expectedUniqueCaoEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setCapEntry_editedCapEntryHasSameIdentity_success() {
//        uniqueCapEntryList.add(ALICE);
//        CapEntry editedAlice = new CapEntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        uniqueCapEntryList.setCapEntry(ALICE, editedAlice);
//        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
//        expectedUniqueCapEntryList.add(editedAlice);
//        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setCapEntry_editedCapEntryHasDifferentIdentity_success() {
//        uniqueCapEntryList.add(ALICE);
//        uniqueCapEntryList.setCapEntry(ALICE, BOB);
//        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
//        expectedUniqueCapEntryList.add(BOB);
//        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setCapEntry_editedCapEntryHasNonUniqueIdentity_throwsDuplicateCapEntryException() {
//        uniqueCapEntryList.add(ALICE);
//        uniqueCapEntryList.add(BOB);
//        thrown.expect(DuplicateCapEntryException.class);
//        uniqueCapEntryList.setPerson(ALICE, BOB);
//    }
//
//    @Test
//    public void remove_nullCapEntry_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.remove(null);
//    }
//
//    @Test
//    public void remove_capEntryDoesNotExist_throwsCapEntryNotFoundException() {
//        thrown.expect(CapEntryNotFoundException.class);
//        uniqueCapEntryList.remove(ALICE);
//    }
//
//    @Test
//    public void remove_existingPerson_removesPerson() {
//        uniqueCapEntryList.add(ALICE);
//        uniqueCapEntryList.remove(ALICE);
//        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
//        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setCapEntryList_nullUniqueCapEntryList_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.setCapEntryList((UniqueCapEntryList) null);
//    }
//
//    @Test
//    public void setCapEntryList_uniqueCapEntryList_replacesOwnListWithProvidedUniqueCapEntryList() {
//        uniqueCapEntryList.add(ALICE);
//        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
//        expectedUniqueCapEntryList.add(BOB);
//        uniqueCapEntryList.setCapEntryList(expectedUniqueCapEntryList);
//        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setPersons_nullList_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueCapEntryList.setCapEntryList((List<CapEntry>) null);
//    }
//
//    @Test
//    public void setPersons_list_replacesOwnListWithProvidedList() {
//        uniqueCapEntryList.add(ALICE);
//        List<CapEntry> capEntryList = Collections.singletonList(BOB);
//        uniqueCapEntryList.setCapEntryList(capEntryList);
//        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
//        expectedUniqueCapEntryList.add(BOB);
//        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
//    }
//
//    @Test
//    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
//        List<CapEntry> listWithDuplicateCapEntries = Arrays.asList(ALICE, ALICE);
//        thrown.expect(DuplicateCapEntryException.class);
//        uniqueCapEntryList.setCapEntryList(listWithDuplicateCapEntries);
//    }
//
//    @Test
//    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
//        thrown.expect(UnsupportedOperationException.class);
//        uniqueCapEntryList.asUnmodifiableObservableList().remove(0);
//    }
//
//
//}
