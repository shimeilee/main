package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.model.Model.PREDICATE_SHOW_ALL_NOTES;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;

/**
 * Lists all note entries in the Notes Manager to the user.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-note";

    public static final String MESSAGE_SUCCESS = "Listed all notes";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
