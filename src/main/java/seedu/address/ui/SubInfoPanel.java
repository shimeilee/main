package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class SubInfoPanel extends UiPart<Region> {
    private static final String FXML = "SubInfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SubInfoPanel.class);

    @FXML
    private ListView<Person> personListView;
    @FXML
    private ListView<CapEntry> capEntryListView;

    public SubInfoPanel() {
        super(FXML);

    }

    /**
     * Updates the SubInfoPanel with Person List.
     * @param personList
     * @param selectedPerson
     * @param onSelectedPersonChange
     * * */
    public void updateSubInfoPanelToPersonList(ObservableList<Person> personList,
                                               ObservableValue<Person> selectedPerson,
                                               Consumer<Person> onSelectedPersonChange) {
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in person list panel changed to : '" + newValue + "'");
            onSelectedPersonChange.accept(newValue);
        });
        selectedPerson.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected person changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected person,
            // otherwise we would have an infinite loop.
            if (Objects.equals(personListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                personListView.getSelectionModel().clearSelection();
            } else {
                int index = personListView.getItems().indexOf(newValue);
                personListView.scrollTo(index);
                personListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Updates the SubInfoPanel with Cap entry List
     * @param capEntryList
     * @param selectedCapEntry
     * @param onSelectedCapEntryChange
     */
    public void updateSubInfoPanelToCapEntryList(ObservableList<CapEntry> capEntryList,
                                               ObservableValue<CapEntry> selectedCapEntry,
                                               Consumer<CapEntry> onSelectedCapEntryChange) {
        capEntryListView.setItems(capEntryList);
        capEntryListView.setCellFactory(listView -> new CapEntryListViewCell());
        capEntryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in cap entry list panel changed to : '" + newValue + "'");
            onSelectedCapEntryChange.accept(newValue);
        });
        selectedCapEntry.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected cap entry changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected person,
            // otherwise we would have an infinite loop.
            if (Objects.equals(capEntryListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                capEntryListView.getSelectionModel().clearSelection();
            } else {
                int index = capEntryListView.getItems().indexOf(newValue);
                capEntryListView.scrollTo(index);
                capEntryListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    class CapEntryListViewCell extends ListCell<CapEntry> {
        @Override
        protected void updateItem(CapEntry capEntry, boolean empty) {
            super.updateItem(capEntry, empty);

            if (empty || capEntry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CapEntryCard(capEntry, getIndex() + 1).getRoot());
            }
        }
    }

}
