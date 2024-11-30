package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final LogoutInputData inputDate = new LogoutInputData(username);
        logoutUseCaseInteractor.execute(inputDate);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        logoutUseCaseInteractor.switchToLoginView();
    }
}
