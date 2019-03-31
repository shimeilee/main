package seedu.ultistudent.logic.commands;

import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.AddressBook;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UserPrefs;

public class ClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitAddressBook();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        expectedModel.commitAddressBook();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
