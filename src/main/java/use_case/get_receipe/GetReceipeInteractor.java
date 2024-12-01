package use_case.get_receipe;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import use_case.logged_in.LoggedInInputData;
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
    public JSONArray execute(GetReceipeInputData getReceipeInputData, LoginInputData logInInputData) {
        final User user = userFactory.create(
                logInInputData.getUsername(),
                logInInputData.getPassword(),
                getReceipeInputData.getHeight(),
                getReceipeInputData.getWeight(),
                getReceipeInputData.getGender(),
                getReceipeInputData.getAge(),
                getReceipeInputData.getMealType(),
                getReceipeInputData.getCuisineType(),
                getReceipeInputData.getAllergy(),
                getReceipeInputData.getIngredient());

        final GetReceipeOutputData getReceipeOutputData = new GetReceipeOutputData(user.getName(),
                false);
        userPresenter.prepareSuccessView(getReceipeOutputData);

        return userDataAccessObject.getReceipe(user);
    }

    @Override
    public void switchToReceiptView() {
        userPresenter.switchToReceiptView();
    }
}
