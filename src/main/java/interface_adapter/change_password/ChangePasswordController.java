package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.get_receipe.GetReceipeInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username, float height, float weight,
                        String gender, int age, String mealType, String cuisineType, String allergy,
                        String[] ingredient) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password,
                height, weight, gender, age);
        final GetReceipeInputData getReceipeInputData = new GetReceipeInputData(height, weight, gender, age,
                mealType, cuisineType, allergy, ingredient);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData, getReceipeInputData);
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     */
    public void switchToChangePasswordView() {
        userChangePasswordUseCaseInteractor.switchToChangePasswordView();
    }
}
