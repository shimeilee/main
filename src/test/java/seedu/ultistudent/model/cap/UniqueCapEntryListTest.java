package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1001;
import static seedu.ultistudent.testutil.TypicalCapEntry.CS1002;
import static seedu.ultistudent.testutil.TypicalCapEntry.CS2101;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.model.cap.exceptions.CapEntryNotFoundException;
import seedu.ultistudent.model.cap.exceptions.DuplicateCapEntryException;
import seedu.ultistudent.testutil.CapEntryBuilder;


public class UniqueCapEntryListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueCapEntryList uniqueCapEntryList = new UniqueCapEntryList();

    @Test
    public void contains_nullCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.contains(null);
    }

    @Test
    public void contains_capEntryNotInList_returnsFalse() {
        assertFalse(uniqueCapEntryList.contains(CS2101));
    }

    @Test
    public void contains_capEntryInList_returnsTrue() {
        uniqueCapEntryList.add(CS2101);
        assertTrue(uniqueCapEntryList.contains(CS2101));
    }

    @Test
    public void contains_capEntryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCapEntryList.add(CS2101);
        CapEntry editedCS2101 = new CapEntryBuilder(CS2101).withModuleGrade(VALID_MODULE_GRADE_CS1001)
                .build();
        assertTrue(uniqueCapEntryList.contains(editedCS2101));
    }

    @Test
    public void add_nullCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.add(null);
    }

    @Test
    public void add_duplicateCapEntry_throwsDuplicateCapEntryException() {
        uniqueCapEntryList.add(CS2101);
        thrown.expect(DuplicateCapEntryException.class);
        uniqueCapEntryList.add(CS2101);
    }

    @Test
    public void setCapEntry_nullTargetCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.setCapEntry(null, CS2101);
    }

    @Test
    public void setCapEntry_nullEditedCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.setCapEntry(CS2101, null);
    }

    @Test
    public void setCapEntry_targetCapEntryNotInList_throwsCapEntryNotFoundException() {
        thrown.expect(CapEntryNotFoundException.class);
        uniqueCapEntryList.setCapEntry(CS2101, CS2101);
    }

    @Test
    public void setCapEntry_editedCapEntryIsSameCapEntry_success() {
        uniqueCapEntryList.add(CS2101);
        uniqueCapEntryList.setCapEntry(CS2101, CS2101);
        UniqueCapEntryList expectedUniqueCaoEntryList = new UniqueCapEntryList();
        expectedUniqueCaoEntryList.add(CS2101);
        assertEquals(expectedUniqueCaoEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntry_editedCapEntryHasSameIdentity_success() {
        uniqueCapEntryList.add(CS2101);
        CapEntry editedCS2101 = new CapEntryBuilder(CS2101).withModuleCode(VALID_MODULE_CODE_CS1001).build();
        uniqueCapEntryList.setCapEntry(CS2101, editedCS2101);
        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
        expectedUniqueCapEntryList.add(editedCS2101);
        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntry_editedCapEntryHasDifferentIdentity_success() {
        uniqueCapEntryList.add(CS2101);
        uniqueCapEntryList.setCapEntry(CS2101, CS1002);
        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
        expectedUniqueCapEntryList.add(CS1002);
        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntry_editedCapEntryHasNonUniqueIdentity_throwsDuplicateCapEntryException() {
        uniqueCapEntryList.add(CS2101);
        uniqueCapEntryList.add(CS1002);
        thrown.expect(DuplicateCapEntryException.class);
        uniqueCapEntryList.setCapEntry(CS2101, CS1002);
    }

    @Test
    public void remove_nullCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.remove(null);
    }

    @Test
    public void remove_capEntryDoesNotExist_throwsCapEntryNotFoundException() {
        thrown.expect(CapEntryNotFoundException.class);
        uniqueCapEntryList.remove(CS2101);
    }

    @Test
    public void remove_existingCapEntry_removesCapEntry() {
        uniqueCapEntryList.add(CS2101);
        uniqueCapEntryList.remove(CS2101);
        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntryList_nullUniqueCapEntryList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.setCapEntryList((UniqueCapEntryList) null);
    }

    @Test
    public void setCapEntryList_uniqueCapEntryList_replacesOwnListWithProvidedUniqueCapEntryList() {
        uniqueCapEntryList.add(CS2101);
        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
        expectedUniqueCapEntryList.add(CS1002);
        uniqueCapEntryList.setCapEntryList(expectedUniqueCapEntryList);
        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntryList_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCapEntryList.setCapEntryList((List<CapEntry>) null);
    }

    @Test
    public void setCapEntryList_replacesOwnListWithProvidedList() {
        uniqueCapEntryList.add(CS2101);
        List<CapEntry> capEntryList = Collections.singletonList(CS1002);
        uniqueCapEntryList.setCapEntryList(capEntryList);
        UniqueCapEntryList expectedUniqueCapEntryList = new UniqueCapEntryList();
        expectedUniqueCapEntryList.add(CS1002);
        assertEquals(expectedUniqueCapEntryList, uniqueCapEntryList);
    }

    @Test
    public void setCapEntryList_listWithDuplicateCapEntryList_throwsDuplicateCapEntryException() {
        List<CapEntry> listWithDuplicateCapEntries = Arrays.asList(CS2101, CS2101);
        thrown.expect(DuplicateCapEntryException.class);
        uniqueCapEntryList.setCapEntryList(listWithDuplicateCapEntries);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueCapEntryList.asUnmodifiableObservableList().remove(0);
    }


}
