package seedu.ultistudent.ui;
/*
import java.util.Objects;
import java.util.function.Consumer;
*/
import java.util.logging.Logger;

import javafx.fxml.FXML;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Region;

import seedu.ultistudent.commons.core.LogsCenter;

/**
 * Icon List view
 */
public class IconListPanel extends UiPart<Region> {
    private static final String FXML = "IconList.fxml";
    private final Logger logger = LogsCenter.getLogger(IconListPanel.class);

    @FXML
    private ColorAdjust homeworkManagerIconBrightness;

    @FXML
    private ColorAdjust notesManagerIconBrightness;

    @FXML
    private ColorAdjust capManagerIconBrightness;

    public IconListPanel() {
        super(FXML);
        setHomeworkManagerIconBrightness(0.4);
        setNotesManagerIconBrightness(0.4);
        setCapManagerIconBrightness(0.4);
    }

    public void setHomeworkManagerIconBrightness(double brightness) {
        homeworkManagerIconBrightness.setBrightness(brightness);
    }

    public void setNotesManagerIconBrightness(double brightness) {
        notesManagerIconBrightness.setBrightness(brightness);
    }

    public void setCapManagerIconBrightness(double brightness) {
        capManagerIconBrightness.setBrightness(brightness);
    }


}
