package use_case.get_receipe;

import entity.User;
import org.json.JSONArray;

import java.util.Map;

/**
 * The interface of the DAO for the Get Receipe Use Case.
 */
public interface GetReceipeUserDataAccessInterface {

    /**
     * Get the receipe according to the BMR.
     * @param user the user whose weight is to be updated
     */
    JSONArray getReceipe(User user);
}
