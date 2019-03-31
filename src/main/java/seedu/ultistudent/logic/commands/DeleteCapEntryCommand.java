package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.CapEntry;

/**
 * Deletes a cap entry identified using it's displayed index from the Cap Manager.
 */
public class DeleteCapEntryCommand extends Command {

    public static final String COMMAND_WORD = "deletecap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the cap entry identified by the index number used in the displayed cap entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CAP_ENTRY_SUCCESS = "Deleted Cap Entry: %1$s";

    private final Index targetIndex;

    public DeleteCapEntryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<CapEntry> lastShownList = model.getFilteredCapEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CAP_ENTRY_DISPLAYED_INDEX);
        }

        CapEntry capEntryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCapEntry(capEntryToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_CAP_ENTRY_SUCCESS, capEntryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCapEntryCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCapEntryCommand) other).targetIndex)); // state check
    }

}
