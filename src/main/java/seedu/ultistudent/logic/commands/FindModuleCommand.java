package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.homework.ModuleCodeContainsKeywordsPredicate;

/**
 * Finds and lists all persons in UltiStudent whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindModuleCommand extends Command {

    public static final String COMMAND_WORD = "find-mod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all homework that has the module code "
            + "entered (case-insensitive) and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101 CS2103T";

    private final ModuleCodeContainsKeywordsPredicate predicate;

    public FindModuleCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredHomeworkList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_HOMEWORK_LISTED_OVERVIEW, model.getFilteredHomeworkList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleCommand // instanceof handles nulls
                && predicate.equals(((FindModuleCommand) other).predicate)); // state check
    }
}
