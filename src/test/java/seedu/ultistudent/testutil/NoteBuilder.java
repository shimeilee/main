package seedu.ultistudent.testutil;

import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.Content;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.NoteName;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_MODULECODE = "CS2103T";
    public static final String DEFAULT_NOTE_NAME = "Tutorial 1";
    public static final String DEFAULT_CONTENT = "";

    private ModuleCode moduleCode;
    private NoteName noteName;
    private Content content;

    public NoteBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULECODE);
        noteName = new NoteName(DEFAULT_NOTE_NAME);
        content = new Content(DEFAULT_CONTENT);
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        moduleCode = noteToCopy.getModuleCode();
        noteName = noteToCopy.getNoteName();
        content = noteToCopy.getContent();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Note} that we are building.
     */
    public NoteBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code NoteName} of the {@code Note} that we are building.
     */
    public NoteBuilder withNoteName(String noteName) {
        this.noteName = new NoteName(noteName);
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code Note} that we are building.
     */
    public NoteBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    public Note build() {
        return new Note(moduleCode, noteName, content);
    }

}
