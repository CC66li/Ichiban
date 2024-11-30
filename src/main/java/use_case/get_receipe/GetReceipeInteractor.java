package use_case.get_receipe;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginInputData;

/**
 * The Get Receipe Interactor.
 */
public class GetReceipeInteractor implements GetReceipeInputBoundary {
    private final GetReceipeUserDataAccessInterface userDataAccessObject;
    private final GetReceipeOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public GetReceipeInteractor(GetReceipeUserDataAccessInterface getReceipeUserDataAccessInterface,
                                GetReceipeOutputBoundary getReceipeOutputBoundary,
                                UserFactory userFactory) {
        this.userDataAccessObject = getReceipeUserDataAccessInterface;
        this.userPresenter = getReceipeOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(GetReceipeInputData getReceipeInputData, LoginInputData loginInputData) {
        final User user = userFactory.create(
                loginInputData.getUsername(),
                loginInputData.getPassword(),
                getReceipeInputData.getHeight(),
                getReceipeInputData.getWeight(),
                getReceipeInputData.getGender(),
                getReceipeInputData.getAge(),
                getReceipeInputData.getMealType(),
                getReceipeInputData.getCuisineType(),
                getReceipeInputData.getAllergy(),
                getReceipeInputData.getIngredient());
        userDataAccessObject.getReceipe(user);

        final GetReceipeOutputData getReceipeOutputData = new GetReceipeOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(getReceipeOutputData);
    }

    @Override
    public void switchToReceiptView() {
        userPresenter.switchToReceiptView();
    }
}
