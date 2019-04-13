package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.note.NoteNameContainsKeywordsPredicate;

/**
 * Finds and lists all notes in UltiStudent whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindNoteCommand extends Command {

    public static final String COMMAND_WORD = "find-note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all "
            + "notes that has the note name "
            + "entered (case-insensitive) and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Tutorial";

    private final NoteNameContainsKeywordsPredicate predicate;

    public FindNoteCommand(NoteNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredNoteList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_NOTE_LISTED_OVERVIEW,
                        model.getFilteredNoteList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNoteCommand // instanceof handles nulls
                && predicate.equals(((FindNoteCommand) other).predicate)); // state check
    }
}
