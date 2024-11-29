package use_case.get_receipe;

import entity.User;

/**
 * The interface of the DAO for the Get Receipe Use Case.
 */
public interface GetReceipeUserDataAccessInterface {

    /**
     * Get the receipe according to the BMR.
     * @param user the user whose weight is to be updated
     */
    void getReceipe(User user);
}
