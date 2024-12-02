package interface_adapter.get_receipe;

import org.json.JSONArray;
import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInputData;
import use_case.login.LoginInputData;

/**
 * The controller for the GetReceipt Use Case.
 */
public class GetReceipeController {

    private final GetReceipeInputBoundary getReceipeInputBoundary;

    public GetReceipeController(GetReceipeInputBoundary getReceipeInputInteractor) {
        this.getReceipeInputBoundary = getReceipeInputInteractor;
    }

    // default
    public GetReceipeController(){
        this.getReceipeInputBoundary = null;
    }

    /**
     * Executes the GetReceipt Use Case.
     * @param username the height of user
     * @param password the height of user
     * @param height the height of user
     * @param weight the weight of user
     * @param gender the gender of user
     * @param age the age of user
     * @param mealType the type of meal the user want
     * @param cuisineType the type of cuisine the user want
     * @param allergy the allergy the user has
     * @param ingredient the ingredient the user have
     */
    public JSONArray execute(String username, String password, float height, float weight, String gender, int age,
                             String mealType, String cuisineType, String allergy, String[] ingredient) {
        final GetReceipeInputData getReceipeInputData = new GetReceipeInputData(height, weight,
                gender, age, mealType, cuisineType, allergy, ingredient);
        final LoginInputData loginInputData = new LoginInputData(username, password);
        return getReceipeInputBoundary.execute(getReceipeInputData, loginInputData);
    }

    public void switchToLogInView() {
        getReceipeInputBoundary.switchToLogInView();
    }
}
