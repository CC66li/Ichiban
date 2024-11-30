package use_case.logged_in;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginInputData;

/**
 * The Signup Interactor.
 */
public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInUserDataAccessInterface userDataAccessObject;
    private final LoggedInOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public LoggedInInteractor(LoggedInUserDataAccessInterface loggedInUserDataAccessInterface,
                              LoggedInOutputBoundary loggedInOutputBoundary,
                              UserFactory userFactory) {
        this.userDataAccessObject = loggedInUserDataAccessInterface;
        this.userPresenter = loggedInOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(LoggedInInputData loggedinInputData, LoginInputData loginInputData) {

        final User user = userFactory.create(loginInputData.getUsername(), loginInputData.getPassword(),
                loggedinInputData.getHeight(), loggedinInputData.getWeight(), loggedinInputData.getGender(),
                loggedinInputData.getAge(), loggedinInputData.getMealType(), loggedinInputData.getCuisineType(),
                loggedinInputData.getAllergy(), loggedinInputData.getIngredient());
        userDataAccessObject.save(user);

        final LoggedInOutputData loggedInOutputData = new LoggedInOutputData(user.getName(), false);
        userPresenter.prepareSuccessView(loggedInOutputData);
    }

    @Override
    public void switchToGetReceiptView() {
        userPresenter.switchToGetReceiptView();
    }
}
