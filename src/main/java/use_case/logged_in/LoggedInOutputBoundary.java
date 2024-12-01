package use_case.logged_in;

import use_case.signup.SignupOutputData;

/**
 * The output boundary for the Signup Use Case.
 */
public interface LoggedInOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LoggedInOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Executes the switch to InputIngredient view use case.
     */
    void switchToInputIngredientView();
}
