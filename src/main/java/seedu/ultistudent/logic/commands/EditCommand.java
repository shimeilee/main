package seedu.ultistudent.logic.commands;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;

/**
 * Guides the users on edit commands in UltiStudent.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": There are 3 different edit commands in UltiStudent.\n"
            + "They are: edit-hw, edit-note, edit-cap"
            + "Type: 'help' for more";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(MESSAGE_USAGE);
    }
}
