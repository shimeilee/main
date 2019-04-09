package seedu.ultistudent.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.note.Note;

/**
 * The NotesManager Main Panel of the App
 */
public class NotesManagerMainPanel extends UiPart<Region> {
    private static final String FXML = "NotesManagerMainPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private TextArea notesText;

    public NotesManagerMainPanel(ObservableValue<Note> selectedNote) {
        super(FXML);

        // To prevent triggering events for typing inside
        getRoot().setOnKeyPressed(Event::consume);
        if (selectedNote.getValue() == null) {
            disableTextArea();
        }

        // Load note page when selected note changes
        selectedNote.addListener((observable, oldValue, newValue)-> {
            if (newValue == null) {
                loadDefaultNotes();
                return;
            }
            loadNotesPage(newValue);
        });
        loadDefaultNotes();
    }

    /**
     * Loads the selected notes page on the main panel
     * @param note
     */
    private void loadNotesPage (Note note) {
        enableTextArea();
        notesText.setText(note.getContent().toString());

        notesText.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String content = notesText.getText();
                if (event.getCode().equals(KeyCode.ENTER)) {
                    content += '\n';
                } else if (event.isAltDown() || event.isShortcutDown() || event.isShortcutDown()) {
                    // Do Nothing
                } else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                    if (content.length() > 0) {
                        content = content.substring(0, content.length() - 1);
                    } else {
                        content = "";
                    }
                } else {
                    content += event.getText();
                }
                note.setContent(content);
            }
        });
    }

    private void disableTextArea() {
        notesText.setDisable(true);
    }

    private void enableTextArea() {
        notesText.setDisable(false);
    }

    private void loadDefaultNotes () {
        notesText.setPromptText("Key in something");
        notesText.setText("");
        disableTextArea();
    }
}
