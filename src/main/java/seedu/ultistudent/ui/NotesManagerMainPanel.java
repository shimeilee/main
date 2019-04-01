package seedu.ultistudent.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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

    private void loadNotesPage (Note note) {
        notesText.setText(note.getContent().toString());
        notesText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                note.setContent(arg2);
            }
        });
    }

    private void loadDefaultNotes () {
        notesText.setPromptText("Key in something");
    }
}
