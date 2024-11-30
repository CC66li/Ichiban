package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.get_receipe.GetReceipeState;
import interface_adapter.get_receipe.GetReceipeViewModel;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.logged_in.LoggedInOutputData;

/**
 * The Presenter for the LoggedIn Use Case.
 */
public class LoggedInPresenter implements LoggedInOutputBoundary{

    private final LoggedInViewModel loggedInViewModel;
    private final GetReceipeViewModel getReceipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel,
                             GetReceipeViewModel getReceipeViewModel,
                             ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.getReceipeViewModel = getReceipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoggedInOutputData outputData) {

        final GetReceipeState getReceipeState = getReceipeViewModel.getState();
        getReceipeState.setHeight(outputData.getHeight());
        getReceipeState.setWeight(outputData.getWeight());
        getReceipeState.setGender(outputData.getGender());
        getReceipeState.setAge(outputData.getAge());
        getReceipeState.setMealType(outputData.getMealType());
        getReceipeState.setCuisineType(outputData.getCuisineType());
        getReceipeState.setAllergy(outputData.getAllergy());
        getReceipeState.setIngredient(outputData.getIngredient());
        this.getReceipeViewModel.setState(getReceipeState);
        this.getReceipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(getReceipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setLoginError(errorMessage);
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGetReceiptView() {
        viewManagerModel.setState(getReceipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
