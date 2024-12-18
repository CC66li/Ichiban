package use_case.get_receipe;


/**
 * The output boundary for the Change Password Use Case.
 */
public interface GetReceipeOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetReceipeOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Executes the switch to receipt generator view use case.
     */
    void switchToLoggedInView();

    /**
     * Executes the switch to input Ingredient generator view use case.
     */
    void switchToInputIngredientView();
}
