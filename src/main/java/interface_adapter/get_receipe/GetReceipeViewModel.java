package interface_adapter.get_receipe;

import interface_adapter.ViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.login.LoginState;

/**
 * The View Model for the GetReceipe prepare ingredients and meal type View.
 */
public class GetReceipeViewModel extends ViewModel<LoggedInState> {

    public void GetReipeViewModel() {
        new GetReceipeViewModel("logged in");
        setState(new LoggedInState());
    }

    public GetReceipeViewModel(String viewName) {
        super(viewName);
    }
}
