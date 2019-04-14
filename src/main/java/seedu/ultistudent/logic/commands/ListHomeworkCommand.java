package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.model.Model.PREDICATE_SHOW_ALL_HOMEWORK;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;

/**
 * Lists all homework in the Homework Manager to the user.
 */
public class ListHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "list-hw";

    public static final String MESSAGE_SUCCESS = "Listed all homework.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredHomeworkList(PREDICATE_SHOW_ALL_HOMEWORK);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
