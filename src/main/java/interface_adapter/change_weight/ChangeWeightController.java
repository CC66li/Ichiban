package interface_adapter.change_weight;

import use_case.change_weight.ChangeWeightInputBoundary;
import use_case.change_weight.ChangeWeightInputData;
import use_case.get_receipe.GetReceipeInputData;

/**
 * Controller for the Change Weight Use Case.
 */
public class ChangeWeightController {
    private final ChangeWeightInputBoundary userChangeWeightUseCaseInteractor;

    public ChangeWeightController(ChangeWeightInputBoundary userChangeWeightUseCaseInteractor) {
        this.userChangeWeightUseCaseInteractor = userChangeWeightUseCaseInteractor;
    }

    /**
     * Executes the Change Weight Use Case.
     * @param weight the new weight
     * @param username the user whose weight to change
     */
    public void execute(String username,String password, float height, float weight,
                        String gender, int age, String mealType, String cuisineType, String allergy,
                        String[] ingredient) {
        final ChangeWeightInputData changeWeightInputData = new ChangeWeightInputData(username, password,
                height, weight, gender, age);
        final GetReceipeInputData getReceipeInputData = new GetReceipeInputData(height, weight, gender, age,
                mealType, cuisineType, allergy, ingredient);

        userChangeWeightUseCaseInteractor.execute(changeWeightInputData, getReceipeInputData);
    }
}
