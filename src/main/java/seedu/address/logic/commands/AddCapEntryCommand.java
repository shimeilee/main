package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cap.CapEntry;

/**
 * Adds a Cap Entry to the Cap Manager.
 */
public class AddCapEntryCommand extends Command {

    public static final String COMMAND_WORD = "addCapEntry";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a CAP Entry to UltiStudent's CAP Manager.\n"
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULE_CODE "
            + PREFIX_MODULEGRADE + "MODULE_GRADE "
            + PREFIX_MODULECREDITS + "MODULE_CREDITS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_MODULEGRADE + "A+ "
            + PREFIX_MODULECREDITS + "4 "
            + PREFIX_TAG + "Y2S2";

    public static final String MESSAGE_SUCCESS = "New module added to CAP Manager: %1$s";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "This module already exists in the CAP Manager";

    private final CapEntry toAdd;

    /**
     * Creates an AddCAPEntryCommand to add the specified {@code CapEntry}
     */
    public AddCapEntryCommand(CapEntry capEntry) {
        requireNonNull(capEntry);
        toAdd = capEntry;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasCapEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CAP_ENTRY);
        }

        model.addCapEntry(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals (Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCapEntryCommand // instanceof handles nulls
            && toAdd.equals(((AddCapEntryCommand) other).toAdd));
    }
}
