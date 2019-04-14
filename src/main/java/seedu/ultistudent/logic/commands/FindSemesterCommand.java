package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.ModuleSemesterContainsKeywordsPredicate;

/**
 * Finds and lists all cap entries in UltiStudent whose module semester contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSemesterCommand extends Command {

    public static final String COMMAND_WORD = "find-cap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cap entries that was taken at any of the "
            + "semester entered (case-insensitive) and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Y1S1 Y2S2";

    private final ModuleSemesterContainsKeywordsPredicate predicate;

    public FindSemesterCommand(ModuleSemesterContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredCapEntryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CAP_ENTRY_LISTED_OVERVIEW, model.getFilteredCapEntryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSemesterCommand // instanceof handles nulls
                && predicate.equals(((FindSemesterCommand) other).predicate)); // state check
    }
}
