package interface_adapter.get_receipe;

import interface_adapter.ViewManagerModel;

import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInputData;
import use_case.get_receipe.GetReceipeOutputBoundary;
import use_case.get_receipe.GetReceipeOutputData;

/**
 * The Presenter for the GetReceipt Use Case.
 */
public class GetReceipePresenter implements GetReceipeOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    public GetReceipePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetReceipeOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
