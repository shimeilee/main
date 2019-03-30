package seedu.ultistudent.logic.commands;

import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.Test;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}
