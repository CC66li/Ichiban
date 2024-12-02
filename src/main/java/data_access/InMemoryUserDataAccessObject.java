package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.change_weight.ChangeWeightUserDataAccessInterface;
import use_case.get_receipe.GetReceipeInputData;
import use_case.get_receipe.GetReceipeUserDataAccessInterface;
import use_case.logged_in.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the User Data Access Object (DAO).
 * This implementation stores user data temporarily in memory and does NOT
 * persist data between program runs. It also provides an example of fetching
 * recipes via an external API using user preferences.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChangeWeightUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        LoggedInUserDataAccessInterface,
        GetReceipeUserDataAccessInterface{

    // Stores users in memory using their username as the key
    private final Map<String, User> users = new HashMap<>();
    private String currentUsername; // Tracks the currently logged-in user

    // Constants for HTTP API interactions
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String MESSAGE = "message";


    /**
     * Checks if a user exists by their username.
     * @param identifier the username to check
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Saves a new user or updates an existing user in the in-memory store.
     * @param user the User object to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }
    
    /**
     * Retrieves a user by their username.
     * @param username the username of the user to fetch
     * @return the User object, or null if not found
     */
    @Override
    public User get(String username) {
        return users.get(username);
    }

    /**
     * Updates the password of an existing user.
     * @param user the User object with the updated password
     */
    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    /**
     * Updates the weight of an existing user.
     * @param user the User object with the updated weight
     */
    @Override
    public void changeWeight(User user) {
        users.put(user.getName(), user);
    }

    /**
     * Fetches a list of recipes based on the user's preferences.
     * Makes a GET request to the Edamam API to retrieve recipe suggestions.
     * @param user the User object containing preferences for recipes
     * @return a JSONArray of recipe results
     */
    @Override
    public JSONArray getReceipe(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // Prepare input data for the API request
        GetReceipeInputData getReceipeInputData = new GetReceipeInputData(
                user.getHeight(),
                user.getWeight(),
                user.getGender(),
                user.getAge(),
                user.getMealType(),
                user.getCuisineType(),
                user.getAllergy(),
                user.getIngredient());

        // Construct the API request URL
        String requestUrl = "https://api.edamam.com/api/recipes/v2?type=public&app_id=<ff136c6d>&app_key=<009e6cd694e4752490ac362dc7d>";
        if (user.getIngredient() != null){
            for (String item: user.getIngredient()){
                requestUrl += "&q=" + item;
            }
        }
        if (user.getAllergy() != null){
            requestUrl += "&health=" + user.getAllergy();
        }
        if (user.getCuisineType() != null){
            requestUrl += "&cuisineType=" + user.getCuisineType();
        }
        requestUrl += "&calories=0-" + getReceipeInputData.getBMR();

        // Build the HTTP GET request
        final Request request = new Request.Builder()
                .url(requestUrl)
                .method("GET", null)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            // Execute the request and parse the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return responseBody.getJSONArray("hits"); // Return recipe results
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }

        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Sets the current logged-in username.
     * @param name the username of the current user
     */
    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    /**
     * Retrieves the username of the currently logged-in user.
     * @return the username of the current user
     */
    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
