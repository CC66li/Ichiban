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
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChangeWeightUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        LoggedInUserDataAccessInterface,
        GetReceipeUserDataAccessInterface{

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void changeWeight(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public JSONArray getReceipe(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        GetReceipeInputData getReceipeInputData = new GetReceipeInputData(user.getHeight(),
                user.getWeight(),
                user.getGender(),
                user.getAge(),
                user.getMealType(),
                user.getCuisineType(),
                user.getAllergy(),
                user.getIngredient());

        // According to the input get the url
        String[] ingredients = user.getIngredient();
        String requestUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?ingredients=";
        if (ingredients != null) {
            requestUrl += ingredients[0];

            for (int i = 1; i < ingredients.length; i++) {
                requestUrl += "%2C" + ingredients[i];
            }
        }
        requestUrl += "&number=3&ranking=0";

        final Request request = new Request.Builder()
                .url(requestUrl)
                .method("GET", null)
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                final JSONArray responseBody = new JSONArray(response.body());
                return responseBody;

            } else {
                System.out.println("Request failed: " + response.code());
            }

        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
