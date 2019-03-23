package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.cap.CapEntry;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CapEntryCard extends UiPart<Region> {

    private static final String FXML = "CapEntryListCard.fxml";

    public final CapEntry capEntry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleGrade;
    @FXML
    private Label moduleCredits;

    public CapEntryCard(CapEntry capEntry, int displayedIndex) {
        super(FXML);
        this.capEntry = capEntry;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(capEntry.getModuleCode().toString());
        moduleGrade.setText(capEntry.getModuleGrade().toString());
        moduleCredits.setText(capEntry.getModuleCredits().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CapEntryCard)) {
            return false;
        }

        // state check
        CapEntryCard card = (CapEntryCard) other;
        return id.getText().equals(card.id.getText())
                && capEntry.equals(card.capEntry);
    }
}
