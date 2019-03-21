package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CAP_MANAGER;
import static seedu.address.logic.parser.CliSyntax.HOMEWORK_MANAGER;
import static seedu.address.logic.parser.CliSyntax.NOTES_MANAGER;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Opens a manager module in UltiStudent.
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens the respective managers.\n"
            + "Example: " + COMMAND_WORD + " " + HOMEWORK_MANAGER + "\n"
            + "Types of manager available are: "
            + HOMEWORK_MANAGER + ", "
            + NOTES_MANAGER + ", "
            + CAP_MANAGER + ".";

    private static final String MESSAGE_SUCCESS = "is opened";
    private static final String MESSAGE_FAIL = "Unable to open selected manager.\n"
            + "Types of manager available are: "
            + HOMEWORK_MANAGER + ", "
            + NOTES_MANAGER + ", "
            + CAP_MANAGER + ".";

    private final String manager;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public OpenCommand(String manager) {
        requireNonNull(manager);
        this.manager = manager;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        switch (manager) {
        case HOMEWORK_MANAGER:
            return new CommandResult(manager + " " + MESSAGE_SUCCESS);
        case NOTES_MANAGER:
            return new CommandResult(manager + " " + MESSAGE_SUCCESS);
        case CAP_MANAGER:
            return new CommandResult(manager + " " + MESSAGE_SUCCESS);
        default:
            throw new CommandException(MESSAGE_FAIL);
        }
    }
}
