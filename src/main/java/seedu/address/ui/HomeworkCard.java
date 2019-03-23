package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.homework.Homework;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class HomeworkCard extends UiPart<Region> {

    private static final String FXML = "HomeworkListCard.fxml";

    public final Homework homework;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label homeworkName;
    @FXML
    private Label deadline;

    public HomeworkCard(Homework homework, int displayedIndex) {
        super(FXML);
        this.homework = homework;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(homework.getModuleCode().toString());
        homeworkName.setText(homework.getHomeworkName().toString());
        deadline.setText(homework.getDeadline().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HomeworkCard)) {
            return false;
        }

        // state check
        HomeworkCard card = (HomeworkCard) other;
        return id.getText().equals(card.id.getText())
                && homework.equals(card.homework);
    }
}
