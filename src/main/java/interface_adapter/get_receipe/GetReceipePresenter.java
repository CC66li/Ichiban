package interface_adapter.get_receipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;

import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInputData;
import use_case.get_receipe.GetReceipeOutputBoundary;
import use_case.get_receipe.GetReceipeOutputData;
import use_case.logged_in.LoggedInOutputData;
import view.LoggedInView;


/**
 * The Presenter for the GetReceipt Use Case.
 */
public class GetReceipePresenter implements GetReceipeOutputBoundary {

    // GetReceipt can access the generator surface and the login surface from return.
    private final ViewManagerModel viewManagerModel;
    private final GetReceipeViewModel getReceipeViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public GetReceipePresenter(ViewManagerModel viewManagerModel,
                               GetReceipeViewModel getReceipeViewModel,
                               LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getReceipeViewModel = getReceipeViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(GetReceipeOutputData outputData) {
        // on success, switch to login view.
        final GetReceipeState getReceipeState = getReceipeViewModel.getState();
        getReceipeState.setUsername(outputData.getUsername());
        this.getReceipeViewModel.setState(getReceipeState);
        this.getReceipeViewModel.firePropertyChanged();

        viewManagerModel.setState(getReceipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToInputIngredientView() {
        viewManagerModel.setState(getReceipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
