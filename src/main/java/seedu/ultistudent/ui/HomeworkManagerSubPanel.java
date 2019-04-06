package seedu.ultistudent.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Panel containing the list of ModuleCode.
 */
public class HomeworkManagerSubPanel extends UiPart<Region> {
    private static final String FXML = "HomeworkManagerSubPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HomeworkManagerSubPanel.class);

    @FXML
    private ListView<ModuleCode> moduleCodeListView;

    public HomeworkManagerSubPanel(ObservableList<ModuleCode> moduleCodes,
                                   ObservableValue<ModuleCode> selectedModuleCode,
                                   Consumer<ModuleCode> onSelectedModuleCodeChange) {
        super(FXML);
        moduleCodeListView.setItems(moduleCodes);
        moduleCodeListView.setCellFactory(listView -> new ModuleCodeListViewCell());
        moduleCodeListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    logger.fine("Selection in module code list panel changed to : '" + newValue + "'");
                    onSelectedModuleCodeChange.accept(newValue);
                });
        selectedModuleCode.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected module code changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected module code,
            // otherwise we would have an infinite loop.
            if (Objects.equals(moduleCodeListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                moduleCodeListView.getSelectionModel().clearSelection();
            } else {
                int index = moduleCodeListView.getItems().indexOf(newValue);
                moduleCodeListView.scrollTo(index);
                moduleCodeListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleCode} using a {@code ModuleCodeCard}.
     */
    class ModuleCodeListViewCell extends ListCell<ModuleCode> {
        @Override
        protected void updateItem(ModuleCode moduleCode, boolean empty) {
            super.updateItem(moduleCode, empty);

            if (empty || moduleCode == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCodeCard(moduleCode).getRoot());
            }
        }
    }

}
