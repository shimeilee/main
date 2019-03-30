package seedu.ultistudent.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

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
//        HomeworkManagerMainPanel homeworkManagerSubPanel = new HomeworkManagerMainPanel(backingList, selectedPerson, selectedPerson::set);
//        uiPartRule.setUiPart(homeworkManagerSubPanel);
//
//        personListPanelHandle = new PersonListPanelHandle(getChildNode(homeworkManagerSubPanel.getRoot(),
//                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
//    }
}
