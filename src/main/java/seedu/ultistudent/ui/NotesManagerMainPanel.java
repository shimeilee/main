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

    private Note currentNote;
    private EventHandler<KeyEvent> keyEvent;

    public NotesManagerMainPanel(ObservableValue<Note> selectedNote) {
        super(FXML);

        // To prevent triggering events for typing inside
        getRoot().setOnKeyPressed(Event::consume);
        if (selectedNote.getValue() == null) {
            notesText.setDisable(true);
        }

        keyEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String content = notesText.getText();
                if (event.getCode().equals(KeyCode.ENTER)) {
                    content += '\n';
                } else if (event.isAltDown() || event.isControlDown() || event.isShortcutDown()) {
                    //Do nothing if its these keys
                } else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                    if (content.length() != 0) {
                        content = content.substring(0, content.length() - 1);
                    } else {
                        content = "";
                    }
                } else {
                    content += event.getText();
                }
                currentNote.setContent(content);
            }
        };

        // Load note page when selected note changes
        selectedNote.addListener((observable, oldValue, newValue)-> {
            resetNotesText();
            if (newValue == null) {
                loadDefaultNotes();
                return;
            }
            notesText.removeEventFilter(KeyEvent.KEY_PRESSED, keyEvent);
            currentNote = newValue;
            loadNotesPage(newValue);
        });
        loadDefaultNotes();
    }

    /**
     * Loads the selected notes page on the main panel
     * @param note
     */
    private void loadNotesPage (Note note) {
        notesText.setDisable(false);
        notesText.setText(note.getContent().toString());

        notesText.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent);
    }

    /**
     * Clears the text field and disable it
     */
    private void loadDefaultNotes () {
        notesText.removeEventFilter(KeyEvent.KEY_PRESSED, keyEvent);
        notesText.setText("");
        notesText.setPromptText("Key in something");
        notesText.setDisable(true);
    }

    private void resetNotesText() {
        notesText.setText("");
    }
}
