package use_case.get_receipe;

import use_case.get_receipe.GetReceipeInputData;
import use_case.login.LoginInputData;

/**
 * The Get Receipe Use Case.
 */
public interface GetReceipeInputBoundary {

    /**
     * Execute the Get Receipe Use Case.
     * @param getReceipeInputData the input data for this receipt use case
     * @param loginInputData the input data for this use case
     */
    void execute(GetReceipeInputData getReceipeInputData, LoginInputData loginInputData);

    /**
     * Executes the switch to receipt generator view use case.
     */
    void switchToReceiptView();

}
