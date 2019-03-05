package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Objects;

import seedu.address.model.person.Homework;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * AddHomeworkCommand is created with reference to AddCommand
 *
 */
public class AddHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "addHW"; //TODO: change to 'add' in future

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a homework to UltiStudent's homework manager. "
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULECODE "
            + PREFIX_MODULENAME + "MODULENAME "
            + PREFIX_HOMEWORK + "HOMEWORK "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_PRIORITY + "PRIORITY] \n"
            + "Priorities are low by default if not set, and acceptable values are low, normal, high.\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_MODULENAME + "Software Engineering "
            + PREFIX_HOMEWORK + "User Guide Draft 1"
            + PREFIX_PRIORITY + "high";

    public static final String MESSAGE_SUCCESS = "New homework added: %1$s";
    public static final String MESSAGE_DUPLICATE_HOMEWORK = "This homework already exists in UltiStudent";

    private final Homework toAdd;

    /**
     * Creates an AddHomeworkCommand to add the specified {@code Homework}
     */
    public AddHomeworkCommand(Homework homework) {
        requireNonNull(Homework homework);
        toAdd = homework;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasHomework(toAdd)) {     //TODO: add hasHomework method into Model interface
            throw new CommandException(MESSAGE_DUPLICATE_HOMEWORK);
        }

        model.addHomework(toAdd);   //TODO: add addHomework method into Model interface
        model.commitHomework();     //TODO: add commitHomework method into Model interface
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
