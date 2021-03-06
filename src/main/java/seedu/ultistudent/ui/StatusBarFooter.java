package seedu.ultistudent.ui;

import java.time.Clock;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.ultistudent.model.ReadOnlyUltiStudent;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    public static final String SYNC_STATUS_INITIAL = "Not updated yet in this session";
    public static final String SYNC_STATUS_UPDATED = "Last Updated: %s";

    /**
     * Used to generate time stamps.
     *
     * TODO: change clock to an instance variable.
     * We leave it as a static variable because manual dependency injection
     * will require passing down the clock reference all the way from MainApp,
     * but it should be easier once we have factories/DI frameworks.
     */
    private static Clock clock = Clock.systemDefaultZone();

    private static final String FXML = "StatusBarFooter.fxml";

    private static String openedManager = "";

    @FXML
    private Label syncStatus;
    @FXML
    private Label currentManager;


    public StatusBarFooter(String manager, ReadOnlyUltiStudent ultiStudent) {
        super(FXML);
        ultiStudent.addListener(observable -> updateSyncStatus());
        syncStatus.setText(SYNC_STATUS_INITIAL);
        currentManager.setText(manager);
    }

    /**
     * Sets the clock used to determine the current time.
     */
    public static void setClock(Clock clock) {
        StatusBarFooter.clock = clock;
    }

    /**
     * Returns the clock currently in use.
     */
    public static Clock getClock() {
        return clock;
    }

    /**
     * Updates "last updated" status to the current time.
     */
    private void updateSyncStatus() {
        long now = clock.millis();
        String lastUpdated = new Date(now).toString();
        syncStatus.setText(String.format(SYNC_STATUS_UPDATED, lastUpdated));
    }

    /**
     * Updates the footer to the respective manager.
     * @param manager the current manager that is being displayed.
     */
    public void setCurrentManagerText(String manager) {
        currentManager.setText(manager);
        openedManager = manager;
    }

    public static String getCurrentManagerStatus() {
        return openedManager;
    }

}
