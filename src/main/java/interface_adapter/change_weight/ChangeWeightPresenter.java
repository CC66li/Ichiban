package interface_adapter.change_weight;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.change_weight.ChangeWeightOutputBoundary;
import use_case.change_weight.ChangeWeightOutputData;

/**
 * The Presenter for the Change Weight Use Case.
 */
public class ChangeWeightPresenter implements ChangeWeightOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ChangeWeightViewModel changeWeightViewModel;

    public ChangeWeightPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, ChangeWeightViewModel changeWeightViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.changeWeightViewModel = changeWeightViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeWeightOutputData outputData) {
        loggedInViewModel.firePropertyChanged("weight");
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToChangeWeightView() {
        viewManagerModel.setState(changeWeightViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
