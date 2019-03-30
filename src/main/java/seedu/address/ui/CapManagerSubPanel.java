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


/**
 * Panel containing the list of persons.
 */
public class CapManagerSubPanel extends UiPart<Region> {
    private static final String FXML = "CapManagerSubPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CapManagerSubPanel.class);

    @FXML
    private ListView<CapEntry> capEntryListView;

    public CapManagerSubPanel(ObservableList<CapEntry> capEntryList,
                              ObservableValue<CapEntry> selectedCapEntry,
                              Consumer<CapEntry> onSelectedCapEntryChange) {
        super(FXML);
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
     * Custom {@code ListCell} that displays the graphics of a {@code CapEntry} using a {@code CapEntryCard}.
     */
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
