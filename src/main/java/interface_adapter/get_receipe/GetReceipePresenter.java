package interface_adapter.get_receipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInputData;
import use_case.get_receipe.GetReceipeOutputBoundary;
import use_case.get_receipe.GetReceipeOutputData;
import use_case.logged_in.LoggedInOutputData;


/**
 * The Presenter for the GetReceipt Use Case.
 */
public class GetReceipePresenter implements GetReceipeOutputBoundary {

    // GetReceipt can access the generator surface and the login surface from return.
    private final ViewManagerModel viewManagerModel;
    private final GetReceipeViewModel getReceipeViewModel;
    private final LoginViewModel loginViewModel;

    public GetReceipePresenter(ViewManagerModel viewManagerModel,
                               GetReceipeViewModel getReceipeViewModel,
                               LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getReceipeViewModel = getReceipeViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(GetReceipeOutputData outputData) {
        // on success, switch to login view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(outputData.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToReceiptView() {

    }
}
