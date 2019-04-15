package seedu.ultistudent.logic.commands;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;

/**
 * Finds and lists all persons in UltiStudent whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": There are 3 different find commands in UltiStudent.\n"
            + "They are: find-mod, find-note, find-sem.\n"
            + "Type: 'help' for more";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(MESSAGE_USAGE);
    }
}
