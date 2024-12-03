package use_case.change_password;

import entity.User;
import entity.UserFactory;
import use_case.get_receipe.GetReceipeInputData;

/**
 * Handles the Change Password Use Case by implementing the business logic.
 * <p>
 * This interactor creates a new user instance with updated credentials,
 * updates the user's password in the data access layer, and prepares
 * the appropriate output view.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {

    // Dependency to access user data for password change operations
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;

    // Dependency to handle the presentation logic for the output
    private final ChangePasswordOutputBoundary userPresenter;

    // Factory for creating User objects
    private final UserFactory userFactory;

    /**
     * Constructs a new ChangePasswordInteractor.
     *
     * @param changePasswordDataAccessInterface the data access interface for changing passwords
     * @param changePasswordOutputBoundary      the presenter for preparing the output view
     * @param userFactory                       the factory for creating User objects
     */
    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the Change Password Use Case.
     * <p>
     * This method creates a new {@link User} object with updated password information,
     * updates the user's password in the data access object, and prepares a success view
     * for the presenter.
     *
     * @param changePasswordInputData the input data containing user credentials and details
     * @param getReceipeInputData     additional input data related to user preferences for recipes
     */
    @Override
    public void execute(ChangePasswordInputData changePasswordInputData, GetReceipeInputData getReceipeInputData) {
        // Create a new User instance with updated password
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword(),
                                             changePasswordInputData.getHeight(),
                                             changePasswordInputData.getWeight(),
                                             changePasswordInputData.getGender(),
                                             changePasswordInputData.getAge(),
                                             getReceipeInputData.getMealType(),
                                             getReceipeInputData.getCuisineType(),
                                             getReceipeInputData.getAllergy(),
                                             getReceipeInputData.getIngredient());
        // Update the user's password in the data access layer
        userDataAccessObject.changePassword(user);

        // Prepare the success output view
        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    /**
     * Switches to the Change Password view.
     * <p>
     * This method delegates the responsibility to the presenter to update the view.
     */
    @Override
    public void switchToChangePasswordView() {
        userPresenter.switchToChangePasswordView();
    }
}
