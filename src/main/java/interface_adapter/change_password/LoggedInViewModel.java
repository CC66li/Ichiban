package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public static final String MEAL_LABEL = "Meal Type";
    public static final String CUISINE_LABEL = "Cuisine Type";
    public static final String ALLERGY_LABEL = "Have Allergy?";
    public static final String INGREDIENT_LABEL = "Enter Ingredients";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
