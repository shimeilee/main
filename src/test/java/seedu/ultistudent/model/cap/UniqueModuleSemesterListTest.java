package seedu.ultistudent.model.cap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1001;
import static seedu.ultistudent.testutil.TypicalModuleSemester.SEMESTER_SECOND;
import static seedu.ultistudent.testutil.TypicalModuleSemester.SEMESTER_THIRD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.ultistudent.model.cap.exceptions.DuplicateModuleSemesterException;
import seedu.ultistudent.model.cap.exceptions.ModuleSemesterNotFoundException;
import seedu.ultistudent.testutil.ModuleSemesterBuilder;

public class UniqueModuleSemesterListTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueModuleSemesterList uniqueModuleSemesterList = new UniqueModuleSemesterList();

    @Test
    public void contains_nullCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.contains(null);
    }

    @Test
    public void contains_moduleSemesterNotInList_returnsFalse() {
        assertFalse(uniqueModuleSemesterList.contains(SEMESTER_SECOND));
    }

    @Test
    public void contains_moduleSemester_returnsTrue() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        assertTrue(uniqueModuleSemesterList.contains(SEMESTER_SECOND));
    }

    @Test
    public void contains_moduleSemesterWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        ModuleSemester editedSemesterSecond = new ModuleSemesterBuilder(SEMESTER_SECOND).withModuleSemester("Y1S2")
                .build();
        assertTrue(uniqueModuleSemesterList.contains(editedSemesterSecond));
    }

    @Test
    public void add_nullModuleSemester_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.add(null);
    }

    @Test
    public void add_duplicateModuleSemester_throwsDuplicateCapEntryException() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        thrown.expect(DuplicateModuleSemesterException.class);
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
    }

    @Test
    public void setModuleSemester_nullTargetModuleSemester_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.setModuleSemester(null, SEMESTER_SECOND);
    }

    @Test
    public void setModuleSemester_nullEditedModuleSemester_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, null);
    }

    @Test
    public void setModuleSemester_targetModuleSemesterNotInList_throwsModuleSemesterNotFoundException() {
        thrown.expect(ModuleSemesterNotFoundException.class);
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, SEMESTER_SECOND);
    }

    @Test
    public void setModuleSemester_editedModuleSemesterIsSameModuleSemester_success() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, SEMESTER_SECOND);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        expectedUniqueModuleSemesterList.add(SEMESTER_SECOND);
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemester_editedModuleSemesterHasSameIdentity_success() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        ModuleSemester editedSemesterSecond = new ModuleSemesterBuilder(SEMESTER_SECOND)
                .withModuleSemester(VALID_MODULE_SEMESTER_CS1001).build();
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, editedSemesterSecond);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        expectedUniqueModuleSemesterList.add(editedSemesterSecond);
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemester_editedModuleSemesterHasDifferentIdentity_success() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, SEMESTER_SECOND);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        expectedUniqueModuleSemesterList.add(SEMESTER_SECOND);
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemester_editedModuleSemesterHasNonUniqueIdentity_throwsDuplicateModuleSemesterException() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        uniqueModuleSemesterList.add(SEMESTER_THIRD);
        thrown.expect(DuplicateModuleSemesterException.class);
        uniqueModuleSemesterList.setModuleSemester(SEMESTER_SECOND, SEMESTER_THIRD);
    }

    @Test
    public void remove_nullModuleSemester_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.remove(null);
    }

    @Test
    public void remove_moduleSemesterDoesNotExist_throwsModuleSemesterNotFoundException() {
        thrown.expect(ModuleSemesterNotFoundException.class);
        uniqueModuleSemesterList.remove(SEMESTER_SECOND);
    }

    @Test
    public void remove_existingModuleSemester_removesModuleSemester() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        uniqueModuleSemesterList.remove(SEMESTER_SECOND);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemesterList_nullUniqueModuleSemesterList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.setModuleSemesterList((UniqueModuleSemesterList) null);
    }

    @Test
    public void setModuleSemesterList_uniqueModuleSemesterList_replacesOwnListWithProvidedUniqueModuleSemesterList() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        expectedUniqueModuleSemesterList.add(SEMESTER_THIRD);
        uniqueModuleSemesterList.setModuleSemesterList(expectedUniqueModuleSemesterList);
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemesterList_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleSemesterList.setModuleSemesterList((List<ModuleSemester>) null);
    }

    @Test
    public void setModuleSemesterList_replacesOwnListWithProvidedList() {
        uniqueModuleSemesterList.add(SEMESTER_SECOND);
        List<ModuleSemester> moduleSemesterList = Collections.singletonList(SEMESTER_THIRD);
        uniqueModuleSemesterList.setModuleSemesterList(moduleSemesterList);
        UniqueModuleSemesterList expectedUniqueModuleSemesterList = new UniqueModuleSemesterList();
        expectedUniqueModuleSemesterList.add(SEMESTER_THIRD);
        assertEquals(expectedUniqueModuleSemesterList, uniqueModuleSemesterList);
    }

    @Test
    public void setModuleSemesterList_listWithDuplicateModuleSemesterList_throwsDuplicateModuleSemesterException() {
        List<ModuleSemester> listWithDuplicateModuleSemester = Arrays.asList(SEMESTER_SECOND, SEMESTER_SECOND);
        thrown.expect(DuplicateModuleSemesterException.class);
        uniqueModuleSemesterList.setModuleSemesterList(listWithDuplicateModuleSemester);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueModuleSemesterList.asUnmodifiableObservableList().remove(0);
    }

}
