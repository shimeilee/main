package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.model.Model.PREDICATE_SHOW_ALL_CAP_ENTRIES;
import static seedu.ultistudent.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.CapEntry;

/**
 * Reverts the {@code model}'s UltiStudent to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoAddressBook();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredCapEntryList(PREDICATE_SHOW_ALL_CAP_ENTRIES);

        //update capScore
        ObservableList<CapEntry> capEntryList = model.getFilteredCapEntryList();
        double newTotalModuleCredits = 0;
        double newTotalModuleScore = 0;
        for (CapEntry ce : capEntryList) {
            double currentModuleCredits = ce.getModuleCredits().getValue();
            double currentModuleScore = ce.getModuleGrade().getScore();
            newTotalModuleCredits += currentModuleCredits;
            newTotalModuleScore += currentModuleScore * currentModuleCredits;
        }
        CapEntry.setCapScore(newTotalModuleScore / newTotalModuleCredits);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
