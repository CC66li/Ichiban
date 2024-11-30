package use_case.logged_in;

import use_case.signup.SignupInputData;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface LoggedInInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
