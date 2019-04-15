package seedu.ultistudent.logic.commands;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;

/**
 * Lists all persons in the UltiStudent to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": There are 3 different list commands in UltiStudent.\n"
            + "They are: list-hw, list-note, list-cap.\n"
            + "Type: 'help' for more";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(MESSAGE_USAGE);
    }
}
