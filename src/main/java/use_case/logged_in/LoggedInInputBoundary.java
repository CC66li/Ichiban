package use_case.logged_in;

import use_case.login.LoginInputData;
import use_case.signup.SignupInputData;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface LoggedInInputBoundary {

    /**
     * Executes the signup use case.
     * @param loggedinInputData the input data
     * @param loginInputData the input data
     */
    void execute(LoggedInInputData loggedinInputData, LoginInputData loginInputData);

    /**
     * Executes the switch to Get Receipt view use case.
     */
    void switchToGetReceiptView();
}
