package use_case.get_receipe;

import entity.User;
import entity.UserFactory;
import use_case.get_receipe.*;

/**
 * The Get Receipe Interactor.
 */
public class GetReceipeInteractor implements GetReceipeInputBoundary {
    private final GetReceipeUserDataAccessInterface userDataAccessObject;
    private final GetReceipeOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public GetReceipeInteractor(GetReceipeUserDataAccessInterface changePasswordDataAccessInterface,
                                GetReceipeOutputBoundary changePasswordOutputBoundary,
                                UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(GetReceipeInputData getReceipeInputData) {
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword(),
                                             changePasswordInputData.getHeight(),
                                             changePasswordInputData.getWeight(),
                                             changePasswordInputData.getGender(),
                                             changePasswordInputData.getAge());
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }
}
