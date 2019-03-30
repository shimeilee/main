package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.homework.Date;
import seedu.address.model.homework.Homework;
import seedu.address.model.homework.HomeworkName;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class HomeworkManagerSubPanelTest extends GuiUnitTest {
//    private static final ObservableList<Person> TYPICAL_PERSONS =
//            FXCollections.observableList(getTypicalPersons());
//
//    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;
//
//    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();
//    private PersonListPanelHandle personListPanelHandle;
//
//    @Test
//    public void display() {
//        initUi(TYPICAL_PERSONS);
//
//        for (int i = 0; i < TYPICAL_PERSONS.size(); i++) {
//            personListPanelHandle.navigateToCard(TYPICAL_PERSONS.get(i));
//            Person expectedPerson = TYPICAL_PERSONS.get(i);
//            PersonCardHandle actualCard = personListPanelHandle.getPersonCardHandle(i);
//
//            assertCardDisplaysPerson(expectedPerson, actualCard);
//            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
//        }
//    }
//
//    @Test
//    public void selection_modelSelectedPersonChanged_selectionChanges() {
//        initUi(TYPICAL_PERSONS);
//        Person secondPerson = TYPICAL_PERSONS.get(INDEX_SECOND_PERSON.getZeroBased());
//        guiRobot.interact(() -> selectedPerson.set(secondPerson));
//        guiRobot.pauseForHuman();
//
//        PersonCardHandle expectedPerson = personListPanelHandle.getPersonCardHandle(INDEX_SECOND_PERSON.getZeroBased());
//        PersonCardHandle selectedPerson = personListPanelHandle.getHandleToSelectedCard();
//        assertCardEquals(expectedPerson, selectedPerson);
//    }
//
//    /**
//     * Verifies that creating and deleting large number of persons in {@code SubInfoPanel} requires lesser than
//     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
//     */
//    @Test
//    public void performanceTest() {
//        ObservableList<Homework> backingList = createBackingList(10000);
//
//        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
//            initUi(backingList);
//            guiRobot.interact(backingList::clear);
//        }, "Creation and deletion of person cards exceeded time limit");
//    }
//
//    /**
//     * Returns a list of persons containing {@code personCount} persons that is used to populate the
//     * {@code SubInfoPanel}.
//     */
//    private ObservableList<Homework> createBackingList(int homeworkCount) {
//        ObservableList<Person> backingList = FXCollections.observableArrayList();
//        for (int i = 0; i < homeworkCount; i++) {
//            ModuleCode moduleCode = new ModuleCode("CS210" + i);
//            HomeworkName homeworkName = new HomeworkName("Test " + i);
//            Date deadline = new Date("10/10/200" + i);
//            Homework homework = new Homework(moduleCode, homeworkName, deadline);
//            backingList.add(homework);
//        }
//        return backingList;
//    }
//
//    /**
//     * Initializes {@code personListPanelHandle} with a {@code SubInfoPanel} backed by {@code backingList}.
//     * Also shows the {@code Stage} that displays only {@code SubInfoPanel}.
//     */
//    private void initUi(ObservableList<Homework> backingList) {
//        HomeworkManagerSubPanel homeworkManagerSubPanel = new HomeworkManagerSubPanel(backingList, selectedPerson, selectedPerson::set);
//        uiPartRule.setUiPart(homeworkManagerSubPanel);
//
//        personListPanelHandle = new PersonListPanelHandle(getChildNode(homeworkManagerSubPanel.getRoot(),
//                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
//    }
}
