package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public static final String MEAL_LABEL = "Meal Type";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String TITLE_LABEL = "Sign Up View";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
