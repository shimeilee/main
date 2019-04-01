package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.note.Note;

/**
 * Selects a note identified using it's displayed index from the UltiStudent.
 */
public class SelectNoteCommand extends Command {

    public static final String COMMAND_WORD = "selectNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the note identified by index number used in the displayed Notelist.\n"
            + "Parameters: INDEX (must be a positive integer).\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_NOTE_SUCCESS = "Selected note: %1$s";

    public final Index targetIndex;

    public SelectNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Note> filteredNoteList = model.getFilteredNoteList();

        if (targetIndex.getZeroBased() >= filteredNoteList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        model.setSelectedNote(filteredNoteList.get(targetIndex.getZeroBased()));
        return new CommandResult(String.format(MESSAGE_SELECT_NOTE_SUCCESS, targetIndex.getOneBased()));
    }
}
