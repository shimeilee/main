package seedu.ultistudent.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
            notesText.setDisable(true);
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
        notesText.setDisable(false);
        notesText.setText(note.getContent().toString());
        notesText.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String content = note.getContent().content;
                content += event.getCharacter();
                note.setContent(content);
            }
        });
    }

    private void loadDefaultNotes () {
        notesText.setPromptText("Key in something");
    }
}
