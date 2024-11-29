package use_case.change_weight;

import entity.User;
import entity.UserFactory;
import use_case.get_receipe.GetReceipeInputData;

/**
 * The Change Weight Interactor.
 */
public class ChangeWeightInteractor implements ChangeWeightInputBoundary {
    private final ChangeWeightUserDataAccessInterface userDataAccessObject;
    private final ChangeWeightOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangeWeightInteractor(ChangeWeightUserDataAccessInterface changeHeightDataAccessInterface,
                                  ChangeWeightOutputBoundary changeHeightOutputBoundary,
                                  UserFactory userFactory) {
        this.userDataAccessObject = changeHeightDataAccessInterface;
        this.userPresenter = changeHeightOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangeWeightInputData changeWeightInputData, GetReceipeInputData getReceipeInputData) {
        final User user = userFactory.create(changeWeightInputData.getUsername(),
                                             changeWeightInputData.getPassword(),
                                             changeWeightInputData.getHeight(),
                                             changeWeightInputData.getWeight(),
                                             changeWeightInputData.getGender(),
                                             changeWeightInputData.getAge(),
                                             getReceipeInputData.getMealType(),
                                             getReceipeInputData.getCuisineType(),
                                             getReceipeInputData.getAllergy(),
                                             getReceipeInputData.getIngredient());
        userDataAccessObject.changeWeight(user);

        final ChangeWeightOutputData changeWeightOutputData = new ChangeWeightOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changeWeightOutputData);
    }
}
