package interface_adapter.get_receipe;

import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInputData;

/**
 * The controller for the GetReceipt Use Case.
 */
public class GetReceipeController {

    private final GetReceipeInputBoundary getReceipeInputBoundary;

    public GetReceipeController(GetReceipeInputBoundary getReceipeInputBoundary) {
        this.getReceipeInputBoundary = getReceipeInputBoundary;
    }

    /**
     * Executes the GetReceipt Use Case.
     * @param height the height of user
     * @param weight the weight of user
     * @param gender the gender of user
     * @param age the age of user
     * @param mealType the type of meal the user want
     * @param cuisineType the type of cuisine the user want
     * @param allergy the allergy the user has
     * @param ingredient the ingredient the user have
     */
    public void execute(float height, float weight, String gender, int age,
                        String mealType, String cuisineType,String allergy, String[] ingredient) {
        final GetReceipeInputData getReceipeInputData = new GetReceipeInputData(height, weight,
                gender, age, mealType, cuisineType, allergy, ingredient);

        getReceipeInputBoundary.execute(getReceipeInputData);
    }
}
