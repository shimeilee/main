package seedu.ultistudent.logic.commands;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;


/**
 * Guides the users on add commands in UltiStudent
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": There are 3 different add commands in UltiStudent.\n"
            + "They are: add-homework, add-note, add-cap.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(MESSAGE_USAGE);
    }
}
