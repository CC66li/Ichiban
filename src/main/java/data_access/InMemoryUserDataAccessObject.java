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

    private String currentUsername;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

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


        // According to the input get the url

        // TestCase:
//        String requestUrl = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples,banana&number=9&apiKey=f62ece60c5ea4861adfbf94e38c1a16b";

        String[] ingredients = user.getIngredient();
        String requestUrl = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=";

        if (ingredients != null) {
            requestUrl += ingredients[0];

            for (int i = 1; i < ingredients.length; i++) {
                requestUrl += "," + ingredients[i];
            }
        } else{
            throw new IllegalArgumentException("Ingredients cannot be null. Please provide valid ingredients.");
        }
        requestUrl += "&number=3&apiKey=f62ece60c5ea4861adfbf94e38c1a16b";

        // Build the HTTP GET request
        final Request request = new Request.Builder()
                .url(requestUrl)
                .method("GET", null)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();


        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                final JSONArray responseBody = new JSONArray(response.body().string());
                return responseBody;


            }
            else {
                throw new RuntimeException("Failed to fetch recipes: " + response.message());
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
