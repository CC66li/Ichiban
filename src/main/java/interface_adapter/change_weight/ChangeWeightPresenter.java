package interface_adapter.change_weight;

import interface_adapter.change_password.LoggedInViewModel;
import use_case.change_weight.ChangeWeightOutputBoundary;
import use_case.change_weight.ChangeWeightOutputData;

/**
 * The Presenter for the Change Weight Use Case.
 */
public class ChangeWeightPresenter implements ChangeWeightOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    public ChangeWeightPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeWeightOutputData outputData) {
        loggedInViewModel.firePropertyChanged("weight");
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
