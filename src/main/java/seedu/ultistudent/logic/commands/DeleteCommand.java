package seedu.ultistudent.logic.commands;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;


/**
 * Guides the users on delete commands in UltiStudent.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": There are 3 different delete commands in UltiStudent.\n"
            + "They are: delete-hw, delete-note, delete-cap.\n"
            + "Type: 'help' for more";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return new CommandResult(MESSAGE_USAGE);
    }
}
