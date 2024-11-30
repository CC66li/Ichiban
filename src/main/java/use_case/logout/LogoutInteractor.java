package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        final String userName = logoutInputData.getUsername();
        this.userDataAccessObject.setCurrentUsername(null);
        final LogoutOutputData outputData = new LogoutOutputData(userName, false);
        this.logoutPresenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToLoginView() {
        logoutPresenter.switchToLoginView();
    }
}

