package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInputData;
import use_case.login.LoginInputData;

/**
 * Controller for the LoggedIn Use Case.
 */
public class LoggedInController {

    private final LoggedInInputBoundary userLoggedinUseCaseInteractor;

    public LoggedInController(LoggedInInputBoundary loggedInInputBoundary){
        this.userLoggedinUseCaseInteractor = loggedInInputBoundary;
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
    public void execute(String username, String password, float height, float weight, String gender, int age,
                        String mealType, String cuisineType, String allergy, String[] ingredient) {
        final LoggedInInputData loggedInputData = new LoggedInInputData(height, weight,
                gender, age, mealType, cuisineType, allergy, ingredient);
        final LoginInputData loginInputData = new LoginInputData(username, password);

        userLoggedinUseCaseInteractor.execute(loggedInputData, loginInputData);
    }
}
