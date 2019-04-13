package seedu.ultistudent.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * An UI component that displays information of a {@code ModuleCode}.
 */
public class ModuleCodeCard extends UiPart<Region> {

    private static final String FXML = "ModuleCodeCard.fxml";

    public final ModuleCode moduleCode;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCodeLabel;

    public ModuleCodeCard(ModuleCode moduleCode) {
        super(FXML);
        this.moduleCode = moduleCode;
        moduleCodeLabel.setText(moduleCode.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCodeCard)) {
            return false;
        }

        // state check
        ModuleCodeCard card = (ModuleCodeCard) other;
        return id.getText().equals(card.id.getText())
                && moduleCode.equals(card.toString());
    }
}
