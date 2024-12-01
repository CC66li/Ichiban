package use_case.change_password;

import use_case.get_receipe.GetReceipeInputData;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ChangePasswordInputData changePasswordInputData, GetReceipeInputData getReceipeInputData);

    /**
     * Executes the switch to Change Password view use case.
     */
    void switchToChangePasswordView();
}
